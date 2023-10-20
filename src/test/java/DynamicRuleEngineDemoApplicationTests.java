//import com.example.ruleengine.domain.SimCardGroup;

import org.example.DynamicRuleEngineDemoApplication;
import org.example.rules.*;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.YamlRuleDefinitionReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileReader;
import java.io.StringReader;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicRuleEngineDemoApplication.class)

public class DynamicRuleEngineDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void basicRuleTest() {
        StringBuilder sb = new StringBuilder();
        Facts facts = new Facts();
        facts.put("sb", sb);
        Rules rules = new Rules();
        rules.register(new AgeRule());
        RulesEngine rulesEngine = new DefaultRulesEngine();

        int age = 30;
        sb.append("basicRuleTest fire rule engine with age=" + age + "\n");
        facts.put("age", age);
        rulesEngine.fire(rules, facts);

        age = 31;
        sb.append("basicRuleTest fire rule engine with age=" + age + "\n");
        facts.put("age", age);
        rulesEngine.fire(rules, facts);
        System.out.println(sb.toString());
    }


    @Test
    /*
     * 任何一个规则条件不满足，则任何动作都不会执行
     */
    public void unitRuleGroupTest() {
        StringBuilder sb = new StringBuilder();
        Facts facts = new Facts();
        facts.put("sb", sb);
        Rules rules = new Rules();
        rules.register(new EnrollUnitRuleGroup(new AgeRule(), new GenderRule()));
        RulesEngine rulesEngine = new DefaultRulesEngine();

        int age = 30;
        String gender = "male";
        facts.put("age", age);
        facts.put("gender", gender);
        sb.append("unitRuleGroupTest------- fire rule engine with age=" + age + " gender=" + gender + "\n");
        rulesEngine.fire(rules, facts);

        age = 31;
        gender = "male";
        facts.put("age", age);
        facts.put("gender", gender);
        sb.append("unitRuleGroupTest------- fire rule engine with age=" + age + " gender=" + gender + "\n");
        rulesEngine.fire(rules, facts);

        age = 30;
        gender = "female";
        facts.put("age", age);
        facts.put("gender", gender);
        sb.append("unitRuleGroupTest------- fire rule engine with age=" + age + " gender=" + gender + "\n");
        rulesEngine.fire(rules, facts);
        System.out.println(sb.toString());
    }

    @Test
    /*
     * 满足条件的那个规则的动作，如果优先级高会执行
     */
    public void conditionRuleGroupTest() {
        StringBuilder sb = new StringBuilder();
        Facts facts = new Facts();
        facts.put("sb", sb);
        Rules rules = new Rules();
        rules.register(new EnrollConditionalRuleGroup(new AgeRule(), new GenderRule()));
        RulesEngine rulesEngine = new DefaultRulesEngine();

        int age = 30;
        String gender = "male";
        facts.put("age", age);
        facts.put("gender", gender);
        sb.append("conditionRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender + "\n");
        rulesEngine.fire(rules, facts);

        age = 31;
        gender = "male";
        facts.put("age", age);
        facts.put("gender", gender);
        sb.append("conditionRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender + "\n");
        rulesEngine.fire(rules, facts);

        age = 30;
        gender = "female";
        facts.put("age", age);
        facts.put("gender", gender);
        sb.append("conditionRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender + "\n");
        rulesEngine.fire(rules, facts);
        System.out.println(sb.toString());
    }

    @Test
    /*
     * 按优先级顺序，只要满足其中一个规则，立刻执行动作，同时跳过剩下的规则
     */
    public void activationRuleGroupTest() {
        StringBuilder sb = new StringBuilder();
        Facts facts = new Facts();
        facts.put("sb", sb);
        Rules rules = new Rules();
        rules.register(new EnrollActivationRuleGroup(new AgeRule(), new GenderRule()));
        RulesEngine rulesEngine = new DefaultRulesEngine();

        int age = 30;
        String gender = "male";
        facts.put("age", age);
        facts.put("gender", gender);
        sb.append("activationRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender + "\n");
        rulesEngine.fire(rules, facts);

        age = 31;
        gender = "male";
        facts.put("age", age);
        facts.put("gender", gender);
        sb.append("activationRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender + "\n");
        rulesEngine.fire(rules, facts);

        age = 30;
        gender = "female";
        facts.put("age", age);
        facts.put("gender", gender);
        sb.append("activationRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender + "\n");
        rulesEngine.fire(rules, facts);
        System.out.println(sb.toString());
    }


    @Test
    public void basicRuleBuilderTest() {
        StringBuilder sb = new StringBuilder();
        Facts facts = new Facts();
        facts.put("sb", sb);
        Rules rules = new Rules();

        // 使用RuleBuilder构建规则
        Rule ageRule = new RuleBuilder()
                .name("AgeRule")
                .description("Check if person's age is older than 18 and less than 60")
                .priority(1)
                .when(fact ->  Integer.parseInt(fact.get("age").toString())  <= 30)
                .then(fact -> {
                    StringBuilder s = fact.get("sb");
                    s.append("age is " + fact.get("age") +", pass AgeRule" + "\n");
                })
                .build();

        rules.register(ageRule);

        RulesEngine rulesEngine = new DefaultRulesEngine();

        int age = 30;
        sb.append("basicRuleTest fire rule engine with age=" + age + "\n");
        facts.put("age", age);
        rulesEngine.fire(rules, facts);

        age = 31;
        sb.append("basicRuleTest fire rule engine with age=" + age + "\n");
        facts.put("age", age);
        rulesEngine.fire(rules, facts);
        System.out.println(sb.toString());

    }


}
