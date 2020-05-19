package cn.jerome.test;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Administrator on 2018/9/16 0016.
 */
@Component
@ConfigurationProperties(prefix = "boy")
@Valid
@Profile(value = "dev")
public class BoyProperties {
    private double height;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    private double weight;

    private List<String> habit;
    @NotNull(message = "name can not null")
    private String name;
    @Email
    private String email;

    public List<String> getHabit() {
        return habit;
    }

    public void setHabit(List habit) {
        this.habit = habit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
