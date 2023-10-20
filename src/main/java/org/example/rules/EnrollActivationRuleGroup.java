package org.example.rules;

import org.jeasy.rules.support.ActivationRuleGroup;


public class EnrollActivationRuleGroup extends ActivationRuleGroup {
    public EnrollActivationRuleGroup(Object... rules) {
        for(Object rule : rules ) {
            addRule(rule);
        }
    }
}
