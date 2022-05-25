//  The Luhn Algorithm used to validate a variety of identification numbers, such as credit card numbers, IMEI numbers, National Provider Identifier numbers etc.

const luhnCheck = (num) => {
  const arr = (num + "")
    .split("")
    .reverse()
    .map((x) => parseInt(x));
  const lastDigit = arr.shift();
  let sum = arr.reduce(
    (acc, val, i) =>
      i % 2 !== 0 ? acc + val : acc + ((val *= 2) > 9 ? val - 9 : val),
    0
  );
  sum += lastDigit;
  return sum % 10 === 0;
};

luhnCheck("4485275742308327"); // true
luhnCheck(6011329933655299); //  true
luhnCheck(123456789); // false
