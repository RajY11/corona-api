import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';


class App extends Component{

  constructor(props){
    super(props);
    this.state = {
      data : []
    }
  }

  componentDidMount(){
      fetch('http://localhost:8080/data')
        .then(res => res.json())
        .then(json => {
            this.setState({
              data : json
            })
        });
  }

  render(){

    var {data} = this.state;
    var count =0;
    return(
      <div className ="App">
        <nav class="navbar navbar-light bg-light">
          <span class="navbar-brand mb-0 h1">Corona Data</span>
        </nav>
        <table class="table table-striped table-dark">
          <thead>
            <tr>
              <th scope="col">Id</th>
              <th scope="col">country</th>
              
              <th scope="col">Confirmed</th>
              <th scope="col">Death</th>
              <th scope="col">Recovered</th>
            </tr>
          </thead>
          <tbody>
          {data.map(d=>(
            <tr key={d.country1}>
              
          <td>{count+=1}</td>
              <td>{d.country1}</td>
              
              <td>{d.confirmed}</td>
              <td>{d.death}</td>
              <td>{d.recovered}</td>
            </tr>
          ))}
          </tbody>
        </table>
       </div>
    )
  }
}
export default App;
