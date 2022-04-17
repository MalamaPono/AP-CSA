// Lights up and turns off light bulb based on when you click button.
var Bulb = React.createClass({
 getInitialState:function(){
   return{
    status:false
   }
 },
 switch:function(){
  this.setState(
   {status:!this.state.status}
  );
 },
 render:function(){
  var dispBulb = this.state.status ? 'images/unlit.png':'images/lightbulb.png';
  return(
   <div>
    <img src={dispBulb}/>
     <button onClick={this.switch}>Click me</button>
  </div>
  );

 }
});

ReactDOM.render(<Bulb/>,document.getElementById('mydiv'))