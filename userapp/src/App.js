import React, { Component } from 'react';
import axious from 'axios';
import './App.css';


class App extends Component{

  constructor(props){
    super(props);
    this.state = {
      data : [],
      data2 :[],
      email : "",
      countries :"",
      selectedCountries:[]
    };
    this.handleEmailChange = this.handleEmailChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleCountryChange = this.handleCountryChange.bind(this);
  }

  componentDidMount(){
      fetch('http://localhost:8080/data')
        .then(res => res.json())
        .then(json => {
            this.setState({
              data : json,
              data2: json
            })
        });
  }
  handleEmailChange(e){
    this.setState({email:e.target.value});
    
  }

  handleSubmit(event) {
    
    if(this.state.email != null && this.state.countries!=null){
      axious.post("http://localhost:8080/subscribe" , {"emailId" : this.state.email , "countries" : [this.state.countries]})
              .then(response => {
                console.log(response );
                alert("Success, you can add more countires if you want");
              })
              .catch(error=>{
                alert(error);
                console.log(error);
              })
    }

  }

  handleCountryChange(e) {
    var listOfCountries = this.state.selectedCountries;
    listOfCountries.push(e.target.value);
    this.setState({selectedCountries:listOfCountries});
    this.setState({countries:e.target.value});
  }

  render(){

    var {data} = this.state;
    var {data2} = this.state;
    var {selectedCountries} = this.state;
    var count =0;
    let totalCases=0;
    let totalDeaths=0;
    let totalRecovered=0;
    const formatter = new Intl.NumberFormat('en');
    
    

    data.map(d=> {
        totalCases = totalCases + d.confirmed;
        totalDeaths = totalDeaths + d.death;
        totalRecovered = totalRecovered +d.recovered; 
       
        
        }
      )

      let deathPercenteage = ((totalDeaths/totalCases)*100).toFixed(2);
      let recoverPercenteage = ((totalRecovered/totalCases)*100).toFixed(2);
    return(
      <div className ="App">
        <nav className="navbar navbar-light bg-light">
          <span className="navbar-brand mb-0 h1">Corona Data</span><br/>
          <span className="navbar-brand mb-0 h1">Total cases : {formatter.format(totalCases)} | Total Deaths : {formatter.format(totalDeaths)} with {deathPercenteage} %|Total Recovered : {formatter.format(totalRecovered)} with {recoverPercenteage} %</span>
          <span className="navbar-brand mb-0 h1">You can subscribe with your email to get alerts of speicific country on your choice</span>
          <form className="input-group" >
          <span className="navbar-brand mb-0 h1">
              <span className = "font-weight-normal" >Enter email :</span><input type="email" name = "email" className ="btn btn-light" placeholder="Email" onChange={this.handleEmailChange}/>
              
              <select name="country" className ="btn btn-light" placeholder="country" 
              onChange ={(e) =>{
                this.setState({countries : e.target.value});
                if(e.target.value==="ALL"){  this.setState({data2 : data})}
                else{
                this.setState({data2 : data.filter(d=>d.country1===e.target.value)})}
                var listOfCountries = this.state.selectedCountries;
                listOfCountries.push(e.target.value);
                this.setState({selectedCountries:listOfCountries});
              }}>
                                <option key="ALL" value="ALL">All</option> 
                {data.map((d) => <option key={d.country1} value={d.country1}>{d.country1}</option>)}
              </select>
              <input type="submit" className ="btn btn-dark" value = "Subscribe" onClick={this.handleSubmit}/> 
             
          </span> 
          </form>
         
        </nav>
        
        


        <table className="table table-striped table-dark">
          <thead>
            <tr>
              <th scope="col">Id</th>
              <th scope="col">Country</th>
              <th scope="col">Active Cases</th>
              <th scope="col">Confirmed</th>
              <th scope="col">Death</th>
              <th scope="col">Death %</th>
              <th scope="col">Recovered</th>
              <th scope="col">Recovered %</th>
            </tr>
          </thead>
          <tbody>
          {data2.map(d=>(
            <tr key={d.country1}>
              
          <td>{count+=1}</td>
              <td>{d.country1}</td>
              <td>{formatter.format(d.confirmed - (d.death + d.recovered))}</td>
              <td>{formatter.format(d.confirmed)} </td>
              <td>{formatter.format(d.death)} </td>
              <td>{((d.death/d.confirmed)*100).toFixed(2)}%</td>
              <td>{formatter.format(d.recovered)}</td>
              <td>{((d.recovered/d.confirmed)*100).toFixed(2)}%</td>
            </tr>
          ))}
          </tbody>
        </table>
       </div>
    )
  }
}
export default App;
