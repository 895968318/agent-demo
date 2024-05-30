package com.jd;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;

public class Main {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("agent start");
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("agent main");

        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                try {
                    System.out.println("transform " + className);
                    ClassPool cp = ClassPool.getDefault();
                    System.out.println("cpool " + cp);
                    CtClass cc = cp.get("com.jd.Base");
                    System.out.println("cclass " + cc);
                    CtMethod m = cc.getDeclaredMethod("process");
                    System.out.println("cmethod " + m);
                    m.insertBefore("{ System.out.println(\"start\"); }");
                    m.insertAfter("{ System.out.println(\"end\"); }");

                    System.out.println(cc);
                    return cc.toBytecode();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return null;
            }
        }, true);

        try {
            Class<?> aClass = Class.forName("com.jd.Base");
            System.out.println(aClass);
            inst.retransformClasses(aClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Agent Load Done.");
    }
}