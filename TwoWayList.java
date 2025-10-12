import java.util.Objects;

class Node<T> {
    T value;
    Node<T> prev;
    Node<T> next;

    Node(T value, Node<T> prev, Node<T> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}

abstract class ParentList<T> {
    protected Node<T> head;
    protected Node<T> tail;
    protected Node<T> cursor;
    protected int size;

    private int getStatus;
    private int headStatus;
    private int tailStatus;
    private int rightStatus;
    private int putRightStatus;
    private int putLeftStatus;
    private int removeStatus;
    private int addToEmptyStatus;
    private int replaceStatus;
    private int findStatus;

    // ------------------- статусы -------------------
    public final int GET_EMPTY = 0;
    public final int GET_OK = 1;

    public final int HEAD_EMPTY = 0;
    public final int HEAD_OK = 1;

    public final int TAIL_EMPTY = 0;
    public final int TAIL_OK = 1;

    public final int RIGHT_NOT_RIGHT = 0;
    public final int RIGHT_OK = 1;

    public final int PUT_RIGHT_EMPTY = 0;
    public final int PUT_RIGHT_OK = 1;

    public final int PUT_LEFT_EMPTY = 0;
    public final int PUT_LEFT_OK = 1;

    public final int REMOVE_EMPTY = 0;
    public final int REMOVE_OK = 1;

    public final int ADD_TO_EMPTY_NOT_EMPTY = 0;
    public final int ADD_TO_EMPTY_OK = 1;

    public final int REPLACE_EMPTY = 0;
    public final int REPLACE_OK = 1;

    public final int FIND_EMPTY = 0;
    public final int FIND_OK = 1;
    public final int FIND_NOT_FOUND = 2;

    // ------------------- запросы -------------------

    // постусловие: создан пустой список
    protected ParentList() {
        clear();
    }

    // предусловие: список не пуст
    public T get() {
        if (cursor == null) {
            getStatus = GET_EMPTY;
            return null;
        }
        getStatus = GET_OK;
        return cursor.value;
    }

    public int size() {
        return size;
    }

    public boolean isHead() {
        return cursor != null && cursor.prev == null;
    }

    public boolean isTail() {
        return cursor != null && cursor.next == null;
    }

    public boolean isValue() {
        return cursor != null;
    }

    // ------------------- команды -------------------

    // предусловие: список не пуст
    // постусловие: курсор установлен на первый узел в списке
    public void head() {
        if (cursor == null) {
            headStatus = HEAD_EMPTY;
            return;
        }
        cursor = head;
        headStatus = HEAD_OK;
    }

    // предусловие: список не пуст
    // постусловие: курсор установлен на последний узел в списке
    public void tail() {
        if (cursor == null) {
            tailStatus = TAIL_EMPTY;
            return;
        }
        cursor = tail;
        tailStatus = TAIL_OK;
    }

    // предусловие: правее курсора есть элемент
    // постусловие: курсор сдвинут на один узел вправо
    public void right() {
        if (cursor == null || cursor.next == null) {
            rightStatus = RIGHT_NOT_RIGHT;
            return;
        }
        cursor = cursor.next;
        rightStatus = RIGHT_OK;
    }

    // предусловие: список не пуст
    // постусловие: следом за текущим узлом добавлен новый узел со значением `value`
    public void putRight(T value) {
        if (cursor == null) {
            putRightStatus = PUT_RIGHT_EMPTY;
            return;
        }
        Node<T> n = new Node<>(value, cursor, cursor.next);
        if (cursor.next == null) {
            tail = n;
        } else {
            cursor.next.prev = n;
        }
        cursor.next = n;
        size++;
        putRightStatus = PUT_RIGHT_OK;
    }

    // предусловие: список не пуст
    // постусловие: перед текущим узлом добавлен новый узел со значением `value`
    public void putLeft(T value) {
        if (cursor == null) {
            putLeftStatus = PUT_LEFT_EMPTY;
            return;
        }
        Node<T> n = new Node<>(value, cursor.prev, cursor);
        if (cursor.prev == null) {
            head = n;
            cursor.prev = n;
            size++;
            putLeftStatus = PUT_LEFT_OK;
            return;
        }
        cursor.prev.next = n;
        cursor.prev = n;
        size++;
        putLeftStatus = PUT_LEFT_OK;
    }

    // предусловие: список не пуст
    // постусловие: текущий узел удалён,
    // курсор сместился к правому соседу, если он был,
    // иначе курсор сместился к левому соседу, если он был
    public void remove() {
        if (cursor == null) {
            removeStatus = REMOVE_EMPTY;
            return;
        }
        Node<T> next = cursor.next;
        Node<T> prev = cursor.prev;
        if (prev == null && next == null) {
            clear();
            removeStatus = REMOVE_OK;
            return;
        }
        if (prev == null) {
            next.prev = null;
            head = next;
            cursor = next;
            size--;
            removeStatus = REMOVE_OK;
            return;
        }
        if (next == null) {
            prev.next = null;
            tail = prev;
            cursor = prev;
            size--;
            removeStatus = REMOVE_OK;
            return;
        }
        next.prev = prev;
        prev.next = next;
        cursor = next;
        size--;
        removeStatus = REMOVE_OK;
    }

    // постусловие: из списка удалены все узлы
    public void clear() {
        head = null;
        tail = null;
        cursor = null;
        size = 0;
    }

    // предусловие: список пуст
    // постусловие: в списке один узел, его значение равно `value`
    public void addToEmpty(T value) {
        if (cursor != null) {
            addToEmptyStatus = ADD_TO_EMPTY_NOT_EMPTY;
            return;
        }
        Node<T> n = new Node<>(value, null, null);
        head = n;
        tail = n;
        size++;
        addToEmptyStatus = ADD_TO_EMPTY_OK;
    }

    // постусловие: в конец списка добавлен узел со значением `value`
    public void addTail(T value) {
        tail = new Node<>(value, tail, null);
        size++;
    }

    // предусловие: список не пуст
    // постусловие: значение текущего узла равно `value`
    public void replace(T value) {
        if (cursor == null) {
            replaceStatus = REPLACE_EMPTY;
            return;
        }
        cursor.value = value;
        replaceStatus = REPLACE_OK;
    }

    // постусловие: курсор установлен на следующий узел (по отношению к текущему)
    // со значением `value`, если такой найден
    public void find(T value) {
        if (cursor == null) {
            findStatus = FIND_EMPTY;
            return;
        }
        for (Node<T> curr = cursor; curr != null; curr = curr.next) {
            if (Objects.equals(curr.value, value)) {
                cursor = curr;
                findStatus = FIND_OK;
                return;
            }
        }
        findStatus = FIND_NOT_FOUND;
    }

    // постусловие: в списке отсутствуют элементы со значением `value`
    public void removeAll(T value) {
        if (cursor == null) {
            return;
        }
        for (Node<T> curr = head; curr != null; curr = curr.next) {
            if (Objects.equals(curr.value, value)) {
                remove();
            }
        }
        Node<T> curr = head;
        while (curr != null) {
            if (Objects.equals(curr.value, value)) {
                remove();
                continue;
            }
            curr = curr.next;
        }
    }

    // ------------------- запросы статусов -------------------
    public int getGetStatus() {
        return getStatus;
    }
    public int getHeadStatus() {
        return headStatus;
    }
    public int getTailStatus() {
        return tailStatus;
    }
    public int getRightStatus() {
        return rightStatus;
    }
    public int getPutRightStatus() {
        return putRightStatus;
    }
    public int getPutLeftStatus() {
        return putLeftStatus;
    }
    public int getRemoveStatus() {
        return removeStatus;
    }
    public int getAddToEmptyStatus() {
        return addToEmptyStatus;
    }
    public int getReplaceStatus() {
        return replaceStatus;
    }
    public int getFindStatus() {
        return findStatus;
    }
}

class LinkedList<T> extends ParentList<T> {
    public LinkedList() {
        super();
    }
}

class TwoWayList<T> extends ParentList<T> {
    private int leftStatus;

    public final int LEFT_NO_LEFT = 0;
    public final int LEFT_OK = 1;

    public TwoWayList() {
        super();
    }

    // предусловие: левее курсора есть элемент
    // постусловие: курсор сдвинут на один узел влево
    public void left() {
        if (cursor == null || cursor.prev == null) {
            leftStatus = LEFT_NO_LEFT;
            return;
        }
        cursor = cursor.prev;
        leftStatus = LEFT_OK;
    }

    public int getLeftStatus() {
        return leftStatus;
    }
}