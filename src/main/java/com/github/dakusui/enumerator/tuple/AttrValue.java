package com.github.dakusui.enumerator.tuple;

public class AttrValue<T, U> {
  private final T attr;
  private final U value;

  public AttrValue(T attr, U value) {
    this.attr = attr;
    this.value = value;
  }

  public T attr() {
    return this.attr;
  }

  public U value() {
    return this.value;
  }

  @Override
  public int hashCode() {
    return ((this.attr == null) ? 0 : this.attr.hashCode()) + ((this.value == null) ? 0 : this.value.hashCode());
  }

  @Override
  public boolean equals(Object anotherObject) {
    if (!(anotherObject instanceof AttrValue))
      return false;
    @SuppressWarnings("unchecked")
    AttrValue<Object, Object> another = (AttrValue<Object, Object>) anotherObject;
    return (this.value == null) ? value == null
        : this.value.equals(another.value) && (this.attr == null) ? attr == null : this.attr.equals(another.attr);
  }
}
