package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    // 동시에 멀티스레드에서 hashmap접근하면은 안된다
    // 그럴 땐 ConcurrentHashMap 을 사용한다
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    //저장
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    //조회
    public Item findById(Long id) {
        return store.get(id);
    }

    //전체조회
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    //수정
    public void update(Long itemId, Item updataParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updataParam.getItemName());
        findItem.setPrice(updataParam.getPrice());
        findItem.setQuantity(updataParam.getQuantity());
    }

    //초기화
    public void clearStore() {
        store.clear();
    }
}
