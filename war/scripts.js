function displayAddHeader() {
  var headersTable = document.getElementById("headersTable");
  var rows = headersTable.getElementsByTagName("tr");
  var nextHeaderIndex = (rows.length-1);
  var y = document.createElement('tr');
  y.innerHTML = '<td><input id="requestHeaders.inUse'+nextHeaderIndex+'" name="requestHeaders['+nextHeaderIndex+'].inUse" type="checkbox" checked="yes"/></td><td class="headerField"><input id="requestHeaders.headerKey'+nextHeaderIndex+'" name="requestHeaders['+nextHeaderIndex+'].headerKey" type="text"/></td><td class="headerField">:</td><td class="headerField"><input id="requestHeaders.headerValue'+nextHeaderIndex+'" name="requestHeaders['+nextHeaderIndex+'].headerValue" type="text"/></td>';
  headersTable.appendChild(y);
}
function toggleVisibility(item) {
	if (item.style.visibility == '') item.style.visibility = 'visible'; 
	else item.style.visibility = '';
}