package com.alex.che.stateoflowa;

import com.alex.che.stateoflowa.dto.VoterDTO;
import com.alex.che.stateoflowa.listener.ContextRefreshedEventHandler;
import com.alex.che.stateoflowa.service.VoterService;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DatabaseSetup("/data.xml")
public class VoterServiceIntegrationTest extends BaseTest {

    @MockBean
    private ContextRefreshedEventHandler contextRefreshedEventHandler;

    @Autowired
    private VoterService voterService;

    @Test
    public void getVotersByParamsTestAllParams() {
        List<VoterDTO> voterDTOS = voterService.getVotersByParams("Adair", 9, 2);
        Assert.assertEquals(2, (long) voterDTOS.size());
    }

    @Test
    public void getVotersByParamsTestAllParams_2() {
        List<VoterDTO> voterDTOS = voterService.getVotersByParams("Adair", 2, 2);
        Assert.assertEquals(1, (long) voterDTOS.size());
    }

    @Test
    public void getVotersByParamsTestOnlyCounty() {
        List<VoterDTO> voterDTOS = voterService.getVotersByParams("Adair", null, null);
        Assert.assertEquals(3, (long) voterDTOS.size());
    }

    @Test
    public void getVotersByParamsTestAllParamsDiffCounty() {
        List<VoterDTO> voterDTOS = voterService.getVotersByParams("Muscatine", 3, 2);
        Assert.assertEquals(1, (long) voterDTOS.size());
    }

    @Test
    public void getVotersByParamsTestOnlyCountyDiffCounty() {
        List<VoterDTO> voterDTOS = voterService.getVotersByParams("Muscatine", null, null);
        Assert.assertEquals(2, (long) voterDTOS.size());
    }

    @Test
    public void getVotersByParamsTestOnlyMonth() {
        List<VoterDTO> voterDTOS = voterService.getVotersByParams(null, 9, null);
        Assert.assertEquals(2, (long) voterDTOS.size());
    }

    @Test
    public void getVotersByParamsTestOnlyLimit() {
        List<VoterDTO> voterDTOS = voterService.getVotersByParams(null, null, 1);
        Assert.assertEquals(1, (long) voterDTOS.size());
    }

    @Test
    public void getVotersByParamsTestNoParams() {
        List<VoterDTO> voterDTOS = voterService.getVotersByParams(null, null, null);
        Assert.assertEquals(5, (long) voterDTOS.size());
    }

    @Test
    public void getVotersByParamsTestNotFound() {
        List<VoterDTO> voterDTOS = voterService.getVotersByParams(null, 43, null);
        Assert.assertEquals(0, (long) voterDTOS.size());
    }

}
