var names = ["Mike", "Matt", "Nancy", "Adam", "Jenny", "Nancy", "Carl"];
var uniqueNames = [];
$.each(names, function (i, el) {
  if ($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
});
