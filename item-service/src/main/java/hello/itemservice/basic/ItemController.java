package hello.itemservice.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@AllArgsConstructor
public class ItemController {

    private final ItemRepository repository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = repository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }

    @GetMapping("{itemdId}")
    public String item(Model model, @PathVariable("itemId") Long itemId) {
        Item item = repository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

 //   @PostMapping("/add")
    public String save(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        repository.save(item);

        model.addAttribute("item",item);

        return "basic/item";
    }

  //  @PostMapping("/add")
    public String addItemV1(@ModelAttribute("item") Item item ) {
        repository.save(item);
      //  model.addAttribute("item",item); -> ModelAttribute가 위 메소드를 추가해줌. Model도 생성해줌
        return "basic/item";
    }
    //requestParam 각각 하기 귀찮으니 한꺼번에 추가한다.
    // 요청 파라미터 값 처리 , 모델로 지정한 객체를 자동으로 넣어준다 (Model 추가)

    //@PostMapping("/add")
    public String addItemV2 (Item item) {
        repository.save(item);
        return "basic/item";
    }
    // @ModelAttribute 자체도 생략이 가능하다. 대상 객체는 모델에 자동 등록이 된다.

    //@PostMapping("/add")
    public String addItemV3 (Item item) {
        repository.save(item);
        return "redirect:/basic/item" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV4 (Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = repository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/item/{itemId}";
    }



    @GetMapping("{itemId}/edit")
    public String editForm (@PathVariable("itemId") Long itemId, Model model) {
        Item item = repository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }

    @PostMapping("{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId, @ModelAttribute Item item) {
        repository.update(itemId,item);
        return "redirect:/basic/items/{itemId}";
    }






}
