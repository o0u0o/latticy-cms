package io.github.talelin.latticy.extension.file;

import io.github.talelin.latticy.module.file.Uploader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 文件上传配置类
 *
 * @author Juzi@TaleLin
 * @author colorful@TaleLin
 */
@Configuration(proxyBeanMethods = false)
public class UploaderConfiguration {
    /**
     * @return 本地文件上传实现类
     * 注解@ConditionalOnMissingBean 条件注解 当没有指定时默认实现时使用
     */
    @Bean
    @Order
    @ConditionalOnMissingBean
    public Uploader uploader(){
        //默认的Uploader
        return new LocalUploader();
    }

    /**
     * <h2>七牛云的Uploader</h2>
     * @return
     */
//    @Bean
//    public Uploader qiniuUploader(){
//        return new QiniuUploader();
//    }
}
