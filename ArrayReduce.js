let numbers = [1, 2, 3];
let sum = numbers.reduce(function (previousValue, currentValue) {
  return previousValue + currentValue;
});

console.log(sum);
