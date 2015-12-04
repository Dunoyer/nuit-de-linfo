if (window.console) {
  console.log("Welcome to your Play application's JavaScript!");
}

function selectionProduit(produit)
{
  divInfo = document.getElementById(produit);

  if(divInfo.style.display == 'none')
  {
    divInfo.style.display = 'block';
  }
}


