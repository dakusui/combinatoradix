# combinatoradix
```combinatoradix``` is a Java library which enumerates partial permutations, 
combinations, and repeated combinations of a set that you give to it in a natural 
Java manner and **suitable for concurrent execution**.

About what permutations, combinations, and repeated combinations are in more detail, 
please refer to the Wikipedia articles([4] and [5]).

```combinatoradix``` is created and maintained by Hiroshi Ukai (dakusui@gmail.com).


# Installation
This library requires [Java][7] 6 or later as its execution environment.
To build this library from source, [Maven][7] 3.0.5 or later will be necessary.

## Maven coordinate

```xml

    <dependency>
      <groupId>com.github.dakusui</groupId>
      <artifactId>combinatoradix</artifactId>
      <version>0.8.1</version>
    </dependency>
```


## Building from source
You can build ```combinatoradix``` by getting the source code from github.
```

    $ git clone https://github.com/dakusui/combinatoradix.git
    ...
    $ cd combinatoradix
    ...
    $ mvn package
    ...
    $
```

You will find a compiled jar file ```combinatoradix-{X.Y.Z}-SNAPSHOT.jar``` under
 ```target/``` directory.

# How to use
There are 3 main classes in this library,

+ Permutator (com.github.dakusui.combinatoradix)
+ Combinator (com.github.dakusui.combinatoradix)
+ HomogeniousCombinator (com.github.dakusui.combinatoradix)

All of them can be used in the same manner.
Through the constructor of ```Permutator```, ```Combinator```, or ```HomogeniousCombinator```
you can specify a list of values to be enumerated and how many elements in the set 
should be chosen at once. And then you can iterate all the possible 
partial permutations, combinations, or repeated combinations respectively.

Simplest example to enumerate all the possible combinations in a set would be like following.

```java

      @Test
      public void printCombinations() {
        List<String> list = Arrays.asList("A", "B", "C", "D", "E");
        for (List<String> each : new Combinator<>(list, 2)) {
          System.out.println(each);
        }
      }
```

And more importantly **you can pick up Nth partial permutation, combination, or repeated
combination directly** without iterating upto N.


```java

      @Test
      public void printNthCombinationDirectly() {
        List<String> list = Arrays.asList("A", "B", "C", "D", "E");
        System.out.println(new Combinator<>(list, 2).get(3));
      }
```

This feature will allow you flexible concurrent enumerations.
About the benefit of this behaviour, you can refer to "Why you want to use ```combinatoradix```"
sub-section.

As already mentioned, by changing ```Combinator``` to ```Permutator``` or ```HomogeniousCombinator```,
you can enumerate partial permutations or repeated combinations respectively.
Please refer to "Examples" sections for more samples.


# How it works and why you want to use ```combinatoradix```
## How it works
As its name suggests, this library relies on "factorial number system"[2] "combinatorial number system"[3].
Basic idea is to use a number system where each digit specifies an element in a given set
and map integers from 0 to P(n,k) - 1 to all the k-sequences without repetition on n-set.

For instance, to enumerate permutations P(5,3), ```combinatoradix``` internally uses a number system below


|  2nd|  1st|  0th|
|:---:|:---:|:---:|
|    0|    0|    0|
|    1|    1|    1|
|    2|    2|    2|
|(n/a)|    3|    3|
|(n/a)|(n/a)|    4|

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
We can translate each of those sixty numbers into one permutation respectively for instance,

```

    0 -> (000) -> [Christopher, Bob, Alice]
```

To describe the algorithm, for convenience, let's consider that those are strings and 
suppose a function ```nth(s, i)``` extracts ith digit from a string.
i.e., 

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

And suppose that ```S``` is a number represented in the system above. Now the 
algorithm can be described as following.

```bash

    # "initial" can be [Alice, Bob, Christopher, Derek, Elizabeth], for example.
    initial=[...] 
    remaining=initial
    tmp=[]
    for i in [0 1 2...k-1]; do
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
The detail is discussed in a Wikipedia [article][2].

## Why you want to use ```combinatoradix```

This approach is clearly slower than other normal 'iterating' algorithm such as 
used by "[combinatorics][1]".
But there are still very good reason to use it.

+ **Easy to resume**: As long as you have an index (```combinatoradix```
 classes have ```get(long index): List<T>``` method, which always return the same 
 same list as long as the same index is given to the same type of object created
 from the same constructor parameters.), you can stop and resume your operation at 
 any point. 
+ **Suitable for parallel execution**: For the same reason as the previous one, 
 you can split a big enumeration task into pieces and execute them concurrently.
+ **Predictable order**: As it will be shown in the example, if you give a list 
  sorted in dictionary order, the result will be sorted in the dictionary order, 
  too.

For the first and second point, probably we need some more explanation.
As mentioned, main classes of ```combinatoradix```, all of which extend ```Enumerator<T>```,
have ```get(long index): List<T>``` method and ```size(): long``` method.

And it is guaranteed that the method return the same ```List<T>``` 
if the same initial data set, ```k``` (parameter to specify how many elements
should be picked up from initial data set), and  ```index`` are given. 

Therefore, for instance, ```combinatoradix``` can answer a question like "There
are 26 alphabets from A to Z, if we create 5 character words from them. But each
character can be used only once. What is the 1,000,000th word in dictionary order?".

The answer is ```[D, I, K, H, Q]```.
And it is a single line job for ```combinatoradix``` once data set is given.
 
```java

      @Test
      public void print1000000thWord() {
        List<String> list = Arrays.asList(
            /*
             1    2    3    4    5    6    7    8    9    10
             */
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
            );
        System.out.println(new Permutator<>(list, 5).get(1_000_000));
      }

```

Probably other libraries can enumerate all the 5 character length words faster than
```combinatoradix```. But for figuring out "1,000,000th word", it can be much more 
efficient and do it instantly.
 
This might look a trivial characteristic, when you want to process all the possible
patterns, which can easily become huge number, and you have multiple cores (or servers
 with Hadoop), this becomes useful.

In the above mentioned example, we will have 7,893,600 possible words in total. 
If you have ```N``` cores (```Core_0```, ```Core_1```, ... ```Core_i```,...```Core_N-1```) 
and in order to process all of them as fast as possible what you can do is to 
let ```Core_i``` process words returned by ```get(j)```, where ```j mod N = i```.

# Examples
Most of examples shown in this section is written for Java 7. But by just 
changing ```<>``` into ```<String>``` (or other types depending on contexts),
they should work.

## Enumerating partial permutations (Java7)
To enumerate all the partial permutations, you can use "Permutator" class.
An example follows.

```java

      @Test
      public void printPartialPermutations() {
         List<String> list = Arrays.asList("A", "B", "C", "D", "E");

        for (List<String> each : new Permutator<>(list, 2)) {
          System.out.println(each);
        }
      }
  
```

This program will print output below.

```

    [A, B]
    [A, C]
    [A, D]
    [A, E]
    [B, A]
    [B, C]
    [B, D]
    [B, E]
    [C, A]
    [C, B]
    [C, D]
    [C, E]
    [D, A]
    [D, B]
    [D, C]
    [D, E]
    [E, A]
    [E, B]
    [E, C]
    [E, D]

```

You may notice that the elements are sorted in dictionary order.
This is an important characteristic that ```combinatorix``` offers as already discussed.


## Enumerating combinations  (Java7)

Similarly you can enumerate all the combination just by changing the enumerator
class from ```Permutator``` to ```Combinator```. 

```java

      @Test
      public void printCombinations() {
        List<String> list = Arrays.asList("A", "B", "C", "D", "E");
        for (List<String> each : new Combinator<>(list, 2)) {
          System.out.println(each);
        }
      }
```

Same as ```Permutator``` the output is sorted in dictionary order.

```

    [A, B]
    [A, C]
    [A, D]
    [A, E]
    [B, C]
    [B, D]
    [B, E]
    [C, D]
    [C, E]
    [D, E]
```

## Enumerating repeated combinations  (Java7)

The class to be used for repeated combinations is ```HomogeniousCombinator```.

```java

      public void printRepeatedCombinations() {
        List<String> list = Arrays.asList("A", "B", "C", "D", "E");
        for (List<String> each : new HomogeniousCombinator<>(list, 2)) {
          System.out.println(each);
        }
      }
```

Similarly, dictionary ordered output will be printed.
But an element in the original list can appear multiple times like ```[A,A]```,
```[B,B]```, etc.

```java

    [A, A]
    [A, B]
    [A, C]
    [A, D]
    [A, E]
    [B, B]
    [B, C]
    [B, D]
    [B, E]
    [C, C]
    [C, D]
    [C, E]
    [D, D]
    [D, E]
    [E, E]
```

## Iterate all the partial permutations sequentially (Java7)
Following example will try to find the longest 3-sequence among all possibles.

In real life, please use ```Combinator```, instead of ```Permutator``` if you
really want to know the length of the longest sequence.
Essentially this is doing the 

```java

      @Test
      public void findLongestSequenceSequentially() {
        int lengthOfLongestSequence = 0;
        for (List<String> seq :
            new Permutator<>(
                Arrays.asList("Alice", "Bob", "Christopher", "Derek", "Elizabeth"),
                3)) {
          int l = 0;
          for (String s : seq) {
            l += s.length();
          }
          if (l > lengthOfLongestSequence) lengthOfLongestSequence = l;
        }
        System.out.println(lengthOfLongestSequence);
      }
```

This will print "25"

## Iterate all the partial permutations concurrently (with Java8)
In Jave 8 single node map/reduce is an out-of-box feature. ([single-node MapReduce is available in Java 8][6]).

Following example will try to find the longest 3-sequence among all possibles same as 
the previous one but the difference is that it will be executed concurrently.


```java

      @Test
      public void findLongestSequenceConcurrently() {
        int lengthOfLongestSequence = new EnumeratorAdapter<>(
            new Permutator<>(
                Arrays.asList("Alice", "Bob", "Christopher", "Derek", "Elizabeth"),
                3))
            .parallelStream()
            .map(seq -> {
              int ret = 0;
              for (String each : seq) {
                ret += each.length();
              }
              return ret;
            }).reduce(0, Integer::max);
        System.out.println(lengthOfLongestSequence);
      }
```

```EnumeratorAdapter``` is a trivial adapter class defined as follows.

```java

      public class EnumeratorAdapter<E> extends AbstractList<List<E>> {
        final Enumerator<E> enumerator;
    
        public EnumeratorAdapter(Enumerator<E> enumerator) {
          this.enumerator = enumerator;
        }
    
        @Override
        public List<E> get(int index) {
          return this.enumerator.get(index);
        }
    
        @Override
        public int size() {
          long ret = this.enumerator.size();
          if (ret > Integer.MAX_VALUE) {
            throw new IllegalStateException();
          }
          return (int) ret;
        }
      }
```

This example will print "25", same as the previous one.
Of course you can use the same technique for combinations and repeated combinations, too.


# References
* [1] "combinatorics" library, Christian Trimble
* [2] "Factorial number system", Wikipedia.org
* [3] "Combinatorial number system", Wikipedia.org
* [4] "Permutation", Wikipedia.org
* [5] "Combination", Wikipedia.org
* [6] "mapreduce - Simple Java Map/Reduce framework"
* [7] "Java SE downloads", Oracle
* [8] "Maven - Download Apache Maven"

[1]: https://github.com/ctrimble/combinatorics
[2]: https://en.wikipedia.org/wiki/Factorial_number_system
[3]: https://en.wikipedia.org/wiki/Combinatorial_number_system
[4]: http://en.wikipedia.org/wiki/Permutation
[5]: http://en.wikipedia.org/wiki/Combination
[6]: http://stackoverflow.com/questions/5260212/simple-java-map-reduce-framework
[7]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
[8]: https://maven.apache.org/download.cgi

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

