package com.example.demo.document;
import com.example.demo.validators.NameValidator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;


@Document(collection = "users")
public class User {
    @Id
    @NotNull
    private int id;
    @NotBlank
    @NotNull
    @Size(min = 1, max = 30, message = "Name should be lesser than 30 characters and not empty")
    @NameValidator
    private String name;
    @NotBlank
    @Size(min = 1, max = 100, message = "Address should be non empty and less than 100 characters")
    private String address;
    @Min(value = 0, message = "Age cannot be negative")
    @Max(150)
    @NotNull
    private int age;

    public User(int id, String name, String address, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
