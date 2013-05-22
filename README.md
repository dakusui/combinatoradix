# Enumerator
Welcome to the Enumerator wiki!

"Enumerator" is a Java library which allows you to enumerate permutations, combinations, and repeated combinations of a set that you give it to in a pretty natural Java manner like below,

```java
    List<String> list = new LinkedList<String>();
    list.add("Alice");
    list.add("Beth");
    list.add("Christopher");
    list.add("Derek");
    
    Enumerator<String> permutator = new Permutator<String>(list, 2);
    while (permutations.hasNext()) {
        System.out.println(permutator.next());
    }
```

This program will print
```
[Alice, Beth]
[Alice, Christopher]
[Alice, Derek]
[Beth, Alice]
[Beth, Christopher]
[Beth, Derek]
[Christopher, Alice]
[Christopher, Beth]
[Christopher, Derek]
[Derek, Alice]
[Derek, Beth]
[Derek, Christopher]
```

Enumerator is created and maintained by Hiroshi Ukai.

## Quick start
You can build enumerator by getting the source code from github.
```
    $ git clone https://github.com/dakusui/enumerator.git
    ...
    $ cd enumerator
    ...
    $ mvn install
    ...
    $
```

## How to use
There are 3 main classes in this library,
1. Permutator (jp.jka.dakusui.enumerator)
2. Combinator (jp.jka.dakusui.enumerator)
3. HomogeniousCombinator (jp.jka.dakusui.enumerator)

All of them can be used in the same manner as mentioned above.
Through the constructor you can give the instance a set of values to be enumerated how many elements in the set should be chosen at once to constructors of Permutator, Combinator, or HomogeniousCombinator. And then you can iterate all the possible permutations, combinations, or repeated combinations respectively.

About what permutations, combinations, and repeated combinations are, please refer to the pages below.

* http://en.wikipedia.org/wiki/Permutation
* http://en.wikipedia.org/wiki/Combination

## Copyright and license

Copyright 2013 Hiroshi Ukai.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

  [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

