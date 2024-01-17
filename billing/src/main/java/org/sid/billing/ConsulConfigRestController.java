package org.sid.billing;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConsulConfigRestController {
    private MyConsulConfig myConsulConfig;
    private MyVaultConfig myVaultConfig;
//    @Value("${token.accessTokenTimeout}")
//    private long accessTokenTimeout;
//    @Value("${token.refreshTokenTimeout}")
//    private long refreshTokenTimeout;

    public ConsulConfigRestController(MyConsulConfig myConsulConfig, MyVaultConfig myVaultConfig) {
        this.myConsulConfig = myConsulConfig;
        this.myVaultConfig = myVaultConfig;
    }

//    @GetMapping("/myConfig")
//    public Map<String, Object> myConfig(){
//        return Map.of("accessTokenTimeout", accessTokenTimeout,"refreshTokenTimeout",refreshTokenTimeout);
//    }



    // Method 1:


//    @GetMapping("/myConfig")
//    public MyConsulConfig myConsulConfig(){
//        return myConsulConfig;
//    }
//
//
//    @GetMapping("/myVault")
//    public MyVaultConfig myVaultConfig(){
//        return myVaultConfig;
//    }



    // method 2:
    @GetMapping("/myConfig")
    public Map<String,Object> myConfig(){
        return Map.of("consul config : ",this.myConsulConfig ,"vault config : ",this.myVaultConfig);
    }
}
