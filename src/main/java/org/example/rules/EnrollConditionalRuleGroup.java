package org.example.rules;

import org.jeasy.rules.support.ConditionalRuleGroup;


public class EnrollConditionalRuleGroup extends ConditionalRuleGroup {
    public EnrollConditionalRuleGroup(Object... rules) {
        for(Object rule : rules ) {
            addRule(rule);
        }
    }
}
