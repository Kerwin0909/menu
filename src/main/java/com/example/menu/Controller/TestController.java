package com.example.menu.Controller;

import com.example.menu.Service.ToService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class TestController {

    private final ToService toservice;

    @Autowired
    public TestController(ToService toservice) {
        this.toservice = toservice;
    }

    @PostMapping("/api/login")
    public boolean login(@RequestBody GetLogin g) {
        System.out.println(1);
        return toservice.login(g.getUserName(), g.getPassword(), g.getOption());
    }
    @PostMapping("/api/search")
    public List<Map<String, Object>> search(@RequestBody GetSearch g) {
        return toservice.search(g.getSearchKey(), g.getCategory());
    }
    @PostMapping("/api/searchCard")
    public List<Map<String, Object>> searchCard() {
        return toservice.searchCard();
    }
    @PostMapping("/api/SearchUser")
    public List<Map<String, Object>> SearchUser() {
        return toservice.SearchUser();
    }
    @PostMapping("/api/addRecipe")
    public void addRecipe(@RequestBody AddRecipeRequest req) {
        String name = req.getName();
        String category = req.getCategory();
        String ingredients = req.getIngredients();
        String steps = req.getSteps();
        toservice.addRecipe(name, category, ingredients, steps);
    }
    @PostMapping("/api/addCard")
    public void addCard(@RequestBody AddCardRequest req) {
        toservice.addCard(req.getName(), req.getIntro(), req.getCost());
    }
    @PostMapping("/api/deleteRecipe")
    public void deleteRecipe(@RequestBody DeleteRecipeRequest req) {
        toservice.deleteRecipe(req.getId());
    }
    @PostMapping("/api/deleteCard")
    public void deleteCard(@RequestBody DeleteCardRequest req) {
        toservice.deleteCard(req.getId());
    }
    @PostMapping("/api/editRecipe")
    public void updateRecipe(@RequestBody UpdateRecipeRequest req) {
        toservice.updateRecipe(
                req.getId(),
                req.getName(),
                req.getCategory(),
                req.getIngredients(),
                req.getSteps()
        );
    }
    @PostMapping("/api/editCard")
    public void editCard(@RequestBody EditCardRequest req) {
        toservice.editCard(req.getId(), req.getName(), req.getIntro(), req.getCost());
    }
    @PostMapping("/api/updateUserCost")
    public void updateUserCost(@RequestBody UpdateUserCostRequest req) {
        toservice.updateUserCost(req.getAccount(), req.getCost());
    }
    @PostMapping("/api/getUserCost")
    public Integer getUserCost() {
        return toservice.getUserCost();
    }
    @PostMapping("/api/getTasks")
    public List<Map<String, Object>> getTasks() {
        return toservice.getTasks();
    }
    @PostMapping("/api/completeTask")
    public void completeTask(@RequestBody CompleteTaskRequest req) {
        toservice.completeTask(req.getId());
    }
    @PostMapping("/api/buyCard")
    public void buyCard(@RequestBody BuyCardRequest req) {
        toservice.buyCard(req.getCardid());
    }
    @PostMapping("/api/getUserCards")
    public List<Map<String, Object>> getUserCards() {
        return toservice.getUserCards();
    }
    @PostMapping("/api/doLottery")
    public String doLottery() {
        return toservice.doLottery();
    }
    @PostMapping("/api/giveCardToUser")
    public void giveCardToUser(@RequestBody GiveCardRequest req) {
        toservice.giveCardToUser(req.getCardid());
    }
    @PostMapping("/api/useCard")
    public void useCard(@RequestBody UseCardRequest req) {
        toservice.useCard(req.getId());
    }
}

class GetLogin {
    private String userName;
    private String password;
    private String option;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
class GetSearch {
    private String searchKey;
    private String category;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
class AddRecipeRequest {
    private String name;
    private String category;
    private String ingredients;
    private String steps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
class DeleteRecipeRequest {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
class UpdateRecipeRequest {
    private int id;
    private String name;
    private String category;
    private String ingredients;
    private String steps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
class AddCardRequest {
    private String name;
    private String intro;
    private String cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
class EditCardRequest {
    private int id;
    private String name;
    private String intro;
    private String cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
class DeleteCardRequest {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
class UpdateUserCostRequest {
    private String account;
    private int cost;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
class CompleteTaskRequest {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
class BuyCardRequest {
    private int Cardid;

    public int getCardid() {
        return Cardid;
    }

    public void setCardid(int Cardid) {
        this.Cardid = Cardid;
    }
}
class GiveCardRequest {
    private int Cardid;

    public int getCardid() {
        return Cardid;
    }

    public void setCardid(int Cardid) {
        this.Cardid = Cardid;
    }
}
class UseCardRequest {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}