// click button to display name entered

var refComponent = React.createClass({
 display:function(){
   var name = this.inputdemo.value;
   document.getElementById("dispName").innerHTML = name;
 },
 render:function(){
  return(
   <div>
    <h2>Name:<input type="text" ref={input=>this.inputdemo=input}/></h2><br/>
    <button onClick={this.display}>Click</button>
    <h1>HI...<span id="dispname"></span></h1>
   </div>
  );
 }
});

ReactDOM.render{
 <refComponent/>,document.getElementById("mydiv")
};