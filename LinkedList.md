# АТД LinkedList

## Задание 1:
```java
abstract class ALinkedList<T> {
    // статусы запросов
    public final int GET_NIL = 0;
    public final int GET_OK = 1;
    public final int GET_ERR = 2;

    public final int HEAD_NIL = 0;
    public final int HEAD_OK = 1;
    public final int HEAD_ERR = 2;

    public final int TAIL_NIL = 0;
    public final int TAIL_OK = 1;
    public final int TAIL_ERR = 2;

    public final int RIGHT_NIL = 0;
    public final int RIGHT_OK = 1;
    public final int RIGHT_ERR = 2;

    public final int PUT_RIGHT_NIL = 0;
    public final int PUT_RIGHT_OK = 1;
    public final int PUT_RIGHT_ERR = 2;

    public final int PUT_LEFT_NIL = 0;
    public final int PUT_LEFT_OK = 1;
    public final int PUT_LEFT_ERR = 2;

    public final int REMOVE_NIL = 0;
    public final int REMOVE_OK = 1;
    public final int REMOVE_ERR = 2;

    public final int ADD_TO_EMPTY_NIL = 0;
    public final int ADD_TO_EMPTY_OK = 1;
    public final int ADD_TO_EMPTY_ERR = 2;

    public final int IS_HEAD_NIL = 0;
    public final int IS_HEAD_OK = 1;
    public final int IS_HEAD_ERR = 2;

    public final int IS_TAIL_NIL = 0;
    public final int IS_TAIL_OK = 1;
    public final int IS_TAIL_ERR = 2;

    public final int ADD_TAIL_NIL = 0;
    public final int ADD_TAIL_OK = 1;
    public final int ADD_TAIL_ERR = 2;

    public final int REPLACE_NIL = 0;
    public final int REPLACE_OK = 1;
    public final int REPLACE_ERR = 2;

    public final int FIND_NIL = 0;
    public final int FIND_OK = 1;
    public final int FIND_ERR = 2;

    public final int REMOVE_ALL_NIL = 0;
    public final int REMOVE_ALL_OK = 1;
    public final int REMOVE_ALL_ERR = 2;

    // постусловие: создан новый пустой список
    public abstract ALinkedList<T> AlinkedList(); // конструктор

    // запросы

    // предусловие: список не пуст
    public abstract T get();

    public abstract int size();

    // предусловие: список не пуст
    public abstract boolean isHead();

    // предусловие: список не пуст
    public abstract boolean isTail();

    public abstract boolean isValue();

    // команды

    // предусловие: список не пуст
    // постусловие: курсор установлен на первый узел списка
    public abstract void head();

    // предусловие: список не пуст
    // постусловие: курсор установлен на последний узел списка
    public abstract void tail();

    // предусловие: список не пуст и курсор не установлен на последний узел списка
    // постусловие: курсор смещён на правого соседний узел
    public abstract void right();

    // предусловие: список не пуст
    // постусловие: следом за текущим узлом добавлен новый узел
    public abstract void putRight(T value);

    // предусловие: список не пуст
    // постусловие: перед текущим узлом добавлен новый узел
    public abstract void putLeft(T value);

    // предусловие: список не пуст
    // постусловие: удалён текущий узел. Курсор сместился к следующему узлу, если он был.
    // Иначе курсор сместился к предыдущему узлу, если он был.
    public abstract void remove();

    // постусловие: список пуст
    public abstract void clear();

    // предусловие: список пуст
    // постусловие: в список добавлен новый узел
    public abstract void addToEmpty(T value);

    // предусловие: список не пуст
    // постусловие: в конец списка добавлен новый узел
    public abstract void addTail(T value);

    // предусловие: список не пуст
    // постусловие: значение текущего узла равное value
    public abstract void replace(T value);

    // предусловие: список не пуст
    // постусловие: если следующий узел со значением value найден, то курсор установлен на него
    public abstract void find(T value);

    // предусловие: список не пуст
    // постусловие: в списке отсутствуют узлы со значением value. Курсор смещён аналогично постусловию remove
    public abstract void removeAll(T value);

    // дополнительные запросы

    public abstract int getGetStatus();
    public abstract int getHeadStatus();
    public abstract int getTailStatus();
    public abstract int getRightStatus();
    public abstract int getPutRightStatus();
    public abstract int getPutLeftStatus();
    public abstract int getRemoveStatus();
    public abstract int getAddToEmptyStatus();
    public abstract int getIsHeadStatus();
    public abstract int getIsTailStatus();
    public abstract int getAddTailStatus();
    public abstract int getReplaceStatus();
    public abstract int getFindStatus();
    public abstract int getRemoveAllStatus();
}

```

## Задание 2:
Операция tail не сводима к другим операциям, потому что в АТД среди девяти атомарных операций нет операции, которая 
отвечала бы на вопрос, указывает ли курсор на последний элемент списка.

## Задание 3:
Операция поиска всех узлов с заданным значением уже не нужна, потому что find теперь не будет постоянно находить первый 
попавшийся узел с заданным значением.