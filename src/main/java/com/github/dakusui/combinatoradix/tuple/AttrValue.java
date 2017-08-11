package com.github.dakusui.combinatoradix.tuple;

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
    return ((this.attr == null) ? 0 : this.attr.hashCode())
        + ((this.value == null) ? 0 : this.value.hashCode());
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object anotherObject) {
    if (!(anotherObject instanceof AttrValue))
      return false;
    AttrValue<Object, Object> another = (AttrValue<Object, Object>) anotherObject;
    return equals(this.attr, another.attr) && equals(this.value, another.value);
  }

  public String toString() {
    return "(" + this.attr + "," + this.value + ")";
  }

  private boolean equals(Object a, Object b) {
    if (a == null)
      return b == null;
    return a.equals(b);
  }
}
