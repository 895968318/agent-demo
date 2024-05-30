package com.jd;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class AttachDemo {
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        VirtualMachine vm = VirtualMachine.attach("35188");
        vm.loadAgent("C:\\Users\\89596\\Documents\\workspace\\code\\java\\agent-application\\out\\artifacts\\agent_jar\\agent.jar");
    }
}
