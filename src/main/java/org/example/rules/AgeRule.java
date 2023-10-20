package org.example.rules;


import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;


@Rule
public class AgeRule {

    @Condition
    public boolean above30(@Fact("age") int age ){
        return age <= 30;
    }

    @Action
    public void printAge(Facts facts){
        StringBuilder sb = facts.get("sb");
        sb.append("age is " + facts.get("age") +", pass AgeRule" + "\n");
    }

    @Priority
    public int getPriority(){
        return 0;
    }

    public static void main(String[] args) {
        AgeRule ageRule = new AgeRule();
        int priority = ageRule.getPriority();
        System.out.println(priority);
    }

}
