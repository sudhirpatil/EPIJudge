    StringIntegerInterconversion 6_1
  :: int to char conversion
    (char) (int + '0') , always type cast explicitly
  :: Careful when converting -ve to +ve as -ve max value > +ve max value
  :: think about all the edge cases in input params
    missed -ve numbers
    missed 0 number
    -214748364
  :: pay more attention to comparison conditions in if & loop
    false loop condition x/div > 0 , should be x > 0
  :: reduce the number of intermediate variables
  
    Replace and remove 6_4 ()
  Careful about the array sizes
  Revise program once done
  keep overall flow  in your mental model

  read the question correctly: what are inputs, what is exactly expected ; missed size is of number of characters not the array size
  array index starts with 0 & so max length + 1 = size : confused with size/count with index in array
  Careful about ++/-- in array index : better do it outside index
  prefer for loop over while, as variable initiation/increment etc more clear
  
    ReverseWords6_6
  Missed edge cases : first char space & last word as last char is not space
  last word & case when last char is not space: end = i not i-1

    PhoneNumberMnemonic6_7
   Few problems are very elegantly and easily solved by recursion : find out what kind of patterns we should use recursion?
   When number of for loops are not known or too big, like in this case phone number size not known
   Wherever we need to use stack, which cases to use recursion instead of stack?
  While using recursion, Have clarity on what should be termination point? what is return value? return values from top level functions

  How to calculate O(n) for recursions?
  How to get number of permutations in this case, where each index can represent 4 possible values?