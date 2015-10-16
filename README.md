# combinatoradix
```combinatoradix``` is a Java library which allows you to enumerate permutations, combinations, and repeated combinations of a 
set that you give it to in a pretty natural Java manner like below,

```java

    List<String> list = new LinkedList<String>();
    list.add("Alice");
    list.add("Bob");
    list.add("Christopher");
    list.add("Derek");
    list.add("Elizabeth");
    
    Iterator<List<String>> permutator = new Permutator<String>(list, 2).iterator();
    while (permutations.hasNext()) {
        System.out.println(permutator.next());
    }
```

This program will print following.

```

    [Alice, Bob]
    [Alice, Christopher]
    [Alice, Derek]
    [Alice, Elizabeth]
    [Bob, Alice]
    [Bob, Christopher]
    [Bob, Derek]
    [Bob, Elizabeth]
    [Christopher, Alice]
    [Christopher, Bob]
    [Christopher, Derek]
    [Christopher, Elizabeth]
    [Derek, Alice]
    [Derek, Bob]
    [Derek, Christopher]
    [Derek, Elizabeth]
]
```

```combinatoradix``` is created and maintained by Hiroshi Ukai (dakusui@gmail.com).

## How it works and why you want to use combinatoradix 
As its name suggests, this library relies on "factorial number system"[2] "combinatorial number system"[3].
Basic idea is to use a number system where each digit specifies an element in a given set.
For instance, to enumerate permutations P(5,3), ```combinatoradix``` internally uses a number system below


|  2nd|  1st|  0th|
|:---:|:---:|:---:|
|    0|    0|    0|
|    1|    1|    1|
|    2|    2|    2|
|     |    3|    3|
|     |     |    4|

All the possible numbers in this system are following.

```

     0   1   2   3    4
    10  11  12  13   14
    20  21  22  23   24
    30  31  32  33   34
   100 101 102 103  104
   110 111 112 113  114
   120 121 122 123  124
   130 131 132 133  134
   200 201 202 203  204
   210 211 212 213  214
   220 221 222 223  224
   230 231 232 233  234
```

As shown, there are only 60 numbers and you may notice that P(5, 3) = 5! / 2! = 5 * 4 * 3 * 2 * 1 / 2 * 1 = 60.
We can translate each of those sixty numbers into one permutation.

```

    0 -> (000) -> [Elizabeth, Derek, Christopher, Bob, Alice]
```

For convenience, let's consider that those are strings and suppose a function ```nth(s, i)``` extracts ith digit
from a string.
E.g., 

```

    nth(234, 0) = 4
    nth(234, 1) = 3
    nth(234, 2) = 2
    nth( 31, 2) = 0
```

Then we can come up with a function that translates those strings into decimals as follows.

```

    dec(s) = nth(s, 2) * 4 * 3 + nth(s, 1) * 4 + nth(s, 0)
```

The algorithm is following. Suppose that ```S``` is a number represented in the number system.

```

    remaining=[Alice, Bob, Christopher, Derek, Elizabeth]
    tmp=[]
    for i in [0 1 2]; do
        each_digit=nth(S, i)
        tmp.insert(remaining[each_digt])
    done
    result=tmp

```

That is, if the number is ```234```, 

+ pick up 4 and take the 4th item in [Alice, Bob, Christopher, Derek, Elizabeth], which is "Elizabeth". 
  And it will be inserted in ```tmp```([Elizabeth]).  The remaining elements will be [Alice, Bob, Christopher, Derek].
+ pick up 3 and take the 3rd item in [Alice, Bob, Christopher, Derek], which is "Derek"
  And it will be inserted in ```tmp```([Derek, Elizabeth]).  The remaining elements will be [Alice, Bob, Christopher].
+ pick up 2 and take the 2nd item in [Alice, Bob, Christopher], which is "Christopher".
  And it will be inserted in ```tmp```([Christopher, Derek, Elizabeth]).
+ ```tmp``` will be assigned to ```result```.


Of course we can generalize this number system so that we can apply it to any P(n, k).
The detail is discussed in a Wikipedia article[2].



And this approach is clearly slower than other normal 'iterating' algorithm such as used by "combinatorics"[1].
But there are still very good reason to use it.

+ Predictable order
+ Suitable for parallel execution


# Installation
## Maven coordinate

```xml

    <dependency>
      <groupId>com.github.dakusui</groupId>
      <artifactId>enumerator</artifactId>
      <version>0.6.0</version>
    </dependency>
```


## Building from source
You can build combinatoradix by getting the source code from github.
```

    $ git clone https://github.com/dakusui/combinatoradix.git
    ...
    $ cd combinatoradix
    ...
    $ mvn install
    ...
    $
```


# How to use
There are 3 main classes in this library,
1. Permutator (com.github.dakusui.combinatoradix)
2. Combinator (com.github.dakusui.combinatoradix)
3. HomogeniousCombinator (com.github.dakusui.combinatoradix)

All of them can be used in the same manner as mentioned above.
Through the constructor you can give the instance a set of values to be enumerated how many elements in the set should be chosen at once to constructors of Permutator, Combinator, or HomogeniousCombinator. And then you can iterate all the possible permutations, combinations, or repeated combinations respectively.

About what permutations, combinations, and repeated combinations are, please refer to the pages[4] and [5] presented in 
"References" section.

# References
* [1] "combinatorics" library, Christian Trimble
* [2] "Factorial number system", Wikipedia.org
* [3] "Combinatorial number system", Wikipedia.org
* [4] "Permutation", Wikipedia.org
* [5] "Combination", Wikipedia.org

[1]: https://github.com/ctrimble/combinatorics
[2]: https://en.wikipedia.org/wiki/Factorial_number_system
[3]: https://en.wikipedia.org/wiki/Combinatorial_number_system
[4]: http://en.wikipedia.org/wiki/Permutation
[5]: http://en.wikipedia.org/wiki/Combination

# Copyright and license

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

