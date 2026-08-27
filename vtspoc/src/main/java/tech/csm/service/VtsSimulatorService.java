package tech.csm.service;

import tech.csm.bean.Response;
import tech.csm.bean.VtsSimulatorRequestBean;

public interface VtsSimulatorService {
    Response startSimulation(VtsSimulatorRequestBean requestBean);
}