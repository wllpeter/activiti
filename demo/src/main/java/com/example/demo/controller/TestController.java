package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.PostConstruct;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ActivitiRecord;
import com.example.demo.service.ActivitiRecordService;
import com.example.demo.util.DateUtils;
import com.example.demo.util.InitializationBean;

@RestController
@RequestMapping("/test")
public class TestController {
	
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ActivitiRecordService activitiRecordService;

	@PostConstruct
	public void initProcess() throws FileNotFoundException {
		/*
         	开始加载activiti流程
         */
        File file = org.springframework.util.ResourceUtils.getFile("classpath:processes");
        String processesPath = file.getPath();
        File[] files = file.listFiles();
        for (File f : files) {
        	{
                // 获取文件名
                String fileName = f.getName();
                String filePathName = processesPath + "/" + f.getName();
                ActivitiRecord record = activitiRecordService.queryByForActivitiName(fileName);
                Path path = Paths.get(filePathName);
                BasicFileAttributeView basicview = Files.getFileAttributeView(path, BasicFileAttributeView.class,
                        LinkOption.NOFOLLOW_LINKS);
                BasicFileAttributes attr;
                try {
                    attr = basicview.readAttributes();

                    // 获取文件创建时间
                    Date createDate = new Date(attr.creationTime().toMillis());
                    // 获取文件最后一次修改时间
                    Date lastModifiedDate = new Date(attr.lastModifiedTime().toMillis());
                    if (null != record) {
                        Date dbLastModifiedDate = record.getUdpateTime();
                        if ((null == dbLastModifiedDate) || (null != dbLastModifiedDate
                                && !lastModifiedDate.toString().equals(dbLastModifiedDate.toString())
                                && lastModifiedDate.after(dbLastModifiedDate))) {
                            deployActiviti(filePathName);
                            activitiRecordService.updateActivitiRecordService(record.getId(),
                                    DateUtils.byDate(lastModifiedDate, DateUtils.YYYY_MM_DD_HH_MM_SS));
                        }
                    } else {
                        record = new ActivitiRecord();
                        record.setActivitiName(fileName);
                        record.setDeleaseDate(DateUtils.byDate(createDate, DateUtils.YYYY_MM_DD_HH_MM_SS));
                        if (null != lastModifiedDate) {
                            record.setUdpateTime(DateUtils.byDate(lastModifiedDate, DateUtils.YYYY_MM_DD_HH_MM_SS));
                        }
                        deployActiviti(filePathName);
                        activitiRecordService.saveActivitiRecordService(record, false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        //
        findLastVersionProcessDefinition();
	}
	
	 /**
     * 部署流程文件
     *
     * @param filePathName：全路径文件名
     * @throws FileNotFoundException
     */
    private void deployActiviti(String filePathName) throws FileNotFoundException {
        InputStream fileInputStream = new FileInputStream(filePathName);
        // 文件的扩展名
        String extension = FilenameUtils.getExtension(filePathName);
        // zip或者bar类型的文件用ZipInputStream方式部署
        DeploymentBuilder builder = repositoryService.createDeployment();
        if (extension.equals("zip") || extension.equals("bar")) {
            ZipInputStream zip = new ZipInputStream(fileInputStream);
            builder.addZipInputStream(zip);
        } else {
            // 其他类型的文件直接部署
            builder.addInputStream(filePathName, fileInputStream);
        }
        builder.deploy();
    }
    
    /**
     * 
     * 加载最新的流程定义的id
     *
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private void findLastVersionProcessDefinition() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc().list();
        Map<String,ProcessDefinition> map = new LinkedHashMap<String,ProcessDefinition>();
        if(list != null && list.size() >0){
            for(ProcessDefinition pd:list){
                map.put(pd.getKey(), pd);
            }
        }
        List<ProcessDefinition> pdList = new ArrayList<ProcessDefinition>(map.values());
        if(pdList!=null && pdList.size()>0){
            for(ProcessDefinition pd:pdList){
                System.out.println(pd.getName() + "----" + pd.getId());
                InitializationBean.activitiMap.put(pd.getName(), pd.getId());
            }
        }
    }
    
    @RequestMapping(value="test",method=RequestMethod.POST)
    public void test() {
    	
    }
	
}
