class: inverse, center, middle

# Újdonságok Java 10-től

---

## Tematika 1.

* Local-Variable Type Inference
    * A `var` nem kulcsszó, hanem speciális típus, ezért használható változónévként (visszafele kompatibilitás)
* `Optional` paraméter nélküli `orElseThrow()` metódus `get()` helyett
* Unmodifiable collections
    * `List.copyOf()`, `Set.copyOf()`, és `Map.copyOf()`
    * `Collectors` `toUnmodifiableList()`, `toUnmodifiableSet()`, `toUnmodifiableMap()` metódusok

---

## Intersection types

```
interface A
interface B

class C implements A, B
class D implements A, B

<T> T pick(T a1, T a2) { return a2; }

var l = pick(new C(), new D());

A & B l;
```

---
