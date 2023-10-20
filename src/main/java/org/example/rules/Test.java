package org.example.rules;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.YamlRuleDefinitionReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * <P>Description: 描述.  </P>
 * <P>CALLED BY: 冯立业 </P>
 * <P>UPDATE BY: 冯立业 </P>
 * <P>CREATE DATE: 14:46 2023/10/19</P>
 * <P>UPDATE DATE: 14:46 2023/10/19</P>
 *
 * @author 冯立业
 * @version 1.0
 * @since java 1.8.0
 */
public class Test {
//    public static void main(String[] args) {
//        Rule ageRule = new RuleBuilder()
//                .name("age rule")
//                .description("年龄大于30岁不要")
//                .priority(1)
//                .when(fact ->  Integer.parseInt(fact.get("age").toString())  <= 30)
//                .then(fact -> {
//                    StringBuilder s = fact.get("sb");
//                    s.append("age is " + fact.get("age") +", pass AgeRule" + "\n");
//                })
//                .build();
//
//        System.out.println(ageRule.toString());
//    }

    public static void main(String[] args) throws Exception {
        MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
        Rule rule = ruleFactory.createRule(new FileReader("/Users/fengliye/Downloads/test-easy-rule/src/main/resources/operation-rule.yml"));

        Facts facts = new Facts();
        facts.put("operation", true);
        facts.put("operation1", "1");
        Rules rules = new Rules();
        rules.register(rule);
        RulesEngine engine = new DefaultRulesEngine();
        engine.fire(rules, facts);


    }



}
