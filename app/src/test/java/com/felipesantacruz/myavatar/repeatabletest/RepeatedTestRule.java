package com.felipesantacruz.myavatar.repeatabletest;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RepeatedTestRule implements TestRule {
    @Override
    public Statement apply(Statement base, Description description) {
        Statement result = base;
        RepeatTest repeat = description.getAnnotation(RepeatTest.class);
        if (repeat != null) {
            int times = repeat.times();
            result = new RepeatableTestStatement(times, base);
        }
        return result;
    }
}
