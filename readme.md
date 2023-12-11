Tester L'application :

- lancer la class Application .
- lancer postman ou n'importe quelle autre application qui paermet de faire des appels de web services
- coller ce lien sur l'url : http://localhost:8080/bill/calculateBill
- aller sur l'onglet body en bas, ensuite coller ce JSON de dans
  [
  {
  "name":"book",
  "price":"12.49",
  "quantity":1,
  "productType":"BOOK",
  "isImported":false

  },
  {
  "name":"music CD",
  "price":"14.99",
  "quantity":2,
  "productType":"ART",
  "isImported":false

  },
  {
  "name":"chocolate bar",
  "price":"0.85",
  "quantity":1,
  "productType":"FOOD",
  "isImported":false
  }
  ]
- cliquer sur send pour envoyer la request
- analyser le résulat