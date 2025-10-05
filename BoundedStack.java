import java.util.List;
import java.util.LinkedList;

abstract class ABoundedStack<T> {
    public final int PUSH_NIL = 0; // push() ещё не вызывалась
    public final int PUSH_OK = 0; // последняя push() отработала корректно
    public final int PUSH_ERR = 0; // стек полон

    public final int POP_NIL = 0; // push() ещё не вызывалась
    public final int POP_OK = 1; // последняя pop() отработала нормально
    public final int POP_ERR = 2; // стек пуст

    public final int PEEK_NIL = 0; // push() ещё не вызывалась
    public final int PEEK_OK = 1; // последняя peek() вернула корректное значение
    public final int PEEK_ERR = 2; // стек пуст

    // предусловие: стек не заполнен полностью
    // постусловие: на вершину стека добавлено новое значение
    public abstract void push(final T value);

    // предусловие: стек не пуст
    // постусловие: из стека удалён верхний элемент
    public abstract void pop();

    // предусловие: стек не пуст
    public abstract T peek();

    public abstract int size();

    // постусловие: стек пуст
    public abstract void clear();

    public abstract int getPushStatus(); // возвращает значение PUSH_*
    public abstract int getPopStatus(); // возвращает значение POP_*
    public abstract int getPeekStatus(); // возвращает значение PEEK_*
}

public class BoundedStack<T> extends ABoundedStack<T> {
    private final int capacity;
    private final List<T> stack;
    private int pushStatus;
    private int popStatus;
    private int peekStatus;

    // постусловие: создан стек размера 32
    public BoundedStack() {
        this(32);
    }

    // постусловие: создан стек размера c, если c > 0, иначе создан пустой стек
    public BoundedStack(final int c) {
        this.capacity = Math.max(c, 0);
        this.stack = new LinkedList<>();
        this.pushStatus = PUSH_NIL;
        this.popStatus = POP_NIL;
        this.peekStatus = PEEK_NIL;
    }

    @Override
    public void push(final T value) {
        if (this.stack.size() == this.capacity) {
            this.pushStatus = PUSH_ERR;
            return;
        }
        this.stack.addLast(value);
        this.pushStatus = PUSH_OK;
    }

    @Override
    public void pop() {
        if (this.stack.isEmpty()) {
            this.popStatus = POP_ERR;
            return;
        }
        this.stack.removeLast();
        this.popStatus = POP_OK;
    }

    @Override
    public T peek() {
        if (this.stack.isEmpty()) {
            peekStatus = PEEK_ERR;
            return null;
        }
        T res = this.stack.getLast();
        this.peekStatus = PEEK_OK;
        return res;
    }

    @Override
    public int size() {
        return this.stack.size();
    }

    @Override
    public void clear() {
        this.stack.clear();
        this.pushStatus = PUSH_NIL;
        this.popStatus = POP_NIL;
        this.peekStatus = PEEK_NIL;
    }

    @Override
    public int getPushStatus() {
        return pushStatus;
    }

    @Override
    public int getPopStatus() {
        return popStatus;
    }

    @Override
    public int getPeekStatus() {
        return peekStatus;
    }
}
