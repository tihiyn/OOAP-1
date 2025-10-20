import java.util.LinkedList;
import java.util.List;

abstract class AQueue<T> {

    // ------------------- статусы -------------------

    public final int GET_HEAD_NIL = 0;
    public final int GET_HEAD_OK = 1;
    public final int GET_HEAD_EMPTY = 2;

    public final int DEQUE_NIL = 0;
    public final int DEQUE_OK = 1;
    public final int DEQUE_EMPTY = 2;

    // ------------------- запросы -------------------

    // постусловие: создана пустая очередь
    // public abstract AQueue<T> Queue(); // конструктор

    // предусловие: очередь не пуста
    public abstract T getHead(); //  получить эл-нт из головы очереди

    public abstract int size();


    // ------------------- команды -------------------

    // постусловие: в конец очереди добавлен эл-нт со значением `itm`
    public abstract void enqueue(T itm);

    // предусловие: очередь не пуста
    // постусловие: удалён эл-нт из головы очереди
    public abstract void deque();

    // постусловие: из очереди удалены все эл-ты
    public abstract void clear();


    // ------------------- запросы статусов -------------------

    public abstract int getGetHeadStatus();
    public abstract int getDequeStatus();
}

class Queue<T> extends AQueue<T> {
    private final List<T> storage;
    private int getHeadStatus;
    private int dequeStatus;

    public Queue() {
        storage = new LinkedList<>();
        getHeadStatus = GET_HEAD_NIL;
        dequeStatus = DEQUE_NIL;
    }

    @Override
    public T getHead() {
        if (storage.isEmpty()) {
            getHeadStatus = GET_HEAD_EMPTY;
            return null;
        }
        T head = storage.getLast();
        getHeadStatus = GET_HEAD_OK;
        return head;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void enqueue(T itm) {
        storage.addFirst(itm);
    }

    @Override
    public void deque() {
        if (storage.isEmpty()) {
            dequeStatus = DEQUE_EMPTY;
            return;
        }
        storage.removeLast();
        dequeStatus = DEQUE_OK;
    }

    @Override
    public void clear() {
        storage.clear();
        getHeadStatus = GET_HEAD_NIL;
        dequeStatus = DEQUE_NIL;
    }

    @Override
    public int getGetHeadStatus() {
        return getHeadStatus;
    }

    @Override
    public int getDequeStatus() {
        return dequeStatus;
    }
}
