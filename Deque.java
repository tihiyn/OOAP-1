import java.util.LinkedList;
import java.util.List;

abstract class ParentQueue<T> {
    protected final List<T> storage;

    private int getFrontStatus;
    private int removeFrontStatus;

    public final int GET_FRONT_NIL = 0;
    public final int GET_FRONT_OK = 1;
    public final int GET_FRONT_EMPTY = 2;

    public final int REMOVE_FRONT_NIL = 0;
    public final int REMOVE_FRONT_OK = 1;
    public final int REMOVE_FRONT_EMPTY = 2;

    public ParentQueue() {
        storage = new LinkedList<>();
        getFrontStatus = GET_FRONT_NIL;
        removeFrontStatus = REMOVE_FRONT_NIL;
    }

    public T getFront() {
        if (storage.isEmpty()) {
            getFrontStatus = GET_FRONT_EMPTY;
            return null;
        }
        T front = storage.getLast();
        getFrontStatus = GET_FRONT_OK;
        return front;
    }

    public int size() {
        return storage.size();
    }

    public void addTail(T itm) {
        storage.addFirst(itm);
    }

    public void removeFront() {
        if (storage.isEmpty()) {
            removeFrontStatus = REMOVE_FRONT_EMPTY;
            return;
        }
        storage.removeLast();
        removeFrontStatus = REMOVE_FRONT_OK;
    }

    public int getGetFrontStatus() {
        return getFrontStatus;
    }

    public int getRemoveFrontStatus() {
        return removeFrontStatus;
    }
}

class Queue<T> extends ParentQueue<T> {
    public Queue() {
        super();
    }
}

class Deque<T> extends ParentQueue<T> {
    private int removeTailStatus;
    private int getTailStatus;

    // ------------------- статусы -------------------

    public final int REMOVE_TAIL_NIL = 0;
    public final int REMOVE_TAIL_OK = 1;
    public final int REMOVE_TAIL_EMPTY = 2;

    public final int GET_TAIL_NIL = 0;
    public final int GET_TAIL_OK = 1;
    public final int GET_TAIL_EMPTY = 2;

    // ------------------- запросы -------------------

    public Deque() {
        super();
        removeTailStatus = REMOVE_TAIL_NIL;
        getTailStatus = GET_TAIL_NIL;
    }

    // предусловие: дека не пуста
    public T getTail() {
        if (storage.isEmpty()) {
            getTailStatus = GET_TAIL_EMPTY;
            return null;
        }
        T tail = storage.getFirst();
        getTailStatus = GET_TAIL_OK;
        return tail;
    }

    // ------------------- команды -------------------

    // постусловие: в начало очереди добавлен новый эл-нт со значением `itm`
    public void addFront(T itm) {
        storage.addLast(itm);
    }

    // предусловие: дека не пуста
    // постусловие: удалён элемент из конца очереди
    public void removeTail() {
        if (storage.isEmpty()) {
            removeTailStatus = REMOVE_TAIL_EMPTY;
            return;
        }
        storage.removeFirst();
        removeTailStatus = REMOVE_TAIL_OK;
    }

    // ------------------- запросы статусов -------------------

    public int getRemoveTailStatus() {
        return removeTailStatus;
    }

    public int getGetTailStatus() {
        return getTailStatus;
    }
}
