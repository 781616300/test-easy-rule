package org.example.rules;

import org.jeasy.rules.support.UnitRuleGroup;


public class EnrollUnitRuleGroup extends UnitRuleGroup {
    public EnrollUnitRuleGroup(Object... rules) {
        for(Object rule : rules ) {
            addRule(rule);
        }
    }
}
