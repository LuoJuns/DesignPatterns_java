package chain.enums;

public enum HealthCondition {
    HEALTH("健康"),BAD("糟糕");
    private String healthCondition;

     HealthCondition(String healthCondition){
        this.healthCondition = healthCondition;
    }
}
