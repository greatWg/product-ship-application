import React , {useState} from 'react';

import './App.css';
import axios from 'axios';

function App() {
 
  const [products, setProducts] = useState(null);
  // Fetch data from API 
  const fetchData = async () => {
    const response = await axios.get(
      'http://localhost:8080/product'
    );
      console.log("***** products ******", response, response.data._embedded.product)
    setProducts(response.data._embedded.product);
  };
  // Show the UI for Product Details
  return (
    <div className="App">
      <h1> Product List</h1>
      <h2> Fecht Product list from API and display</h2>
      <div>
        <button className="fetch-button" onClick={fetchData}> Fetch Data products </button>
      </div>
      <table>
        <tr>
          <th>
            <td>Index</td>
            <td>ProductName</td>
            <td>ProdcutQuantity</td>
            <td>ShipDate</td>
            <td>OptionalColumn - maxBusinessDaysToShip</td>
          </th>
        </tr>
        {products &&
            products.map((product, index) => {
              return (
                <tr>
                  <td>{index+1}</td>
                  <td>{product.productName}</td>
                  <td>{product.inventoryQuantity}</td>
                  <td>{product.shipDate}</td>
                  <td>{product.maxBusinessDaysToShip}</td>
                </tr>
              )
            })
        }
      </table>
    </div>
  );
}

export default App;
