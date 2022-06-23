var testArray = [17, 42, 311, 5, 9, 10, 28, 7, 6];
for (var arrayPosition = 0;
  arrayPosition < testArray.length - 1; arrayPosition++) {
    var currentElement = testArray[arrayPosition];
    var nextElement = testArray[arrayPosition + 1];

    if (currentElement >= nextElement) {
      console.log(findMax = currentElement);
    } else {
      console.log(findMax = nextElement);
    }
  }
// this code block only compare two numbers, could not keep  the max number to compare with next number 
