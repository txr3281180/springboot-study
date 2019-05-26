package com.txr.spbbasicstarter.condtional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by xinrui.tian on 2019/5/22
 */
public class WindowsConditional implements Condition{


    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        Environment environment = conditionContext.getEnvironment();

        String osName = environment.getProperty("os.name");

        if (osName.contains("Windows")) {
            return true;
        }

        return false;
    }
}
