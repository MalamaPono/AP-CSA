var styles = {
 color:"red",
 background:"green",
 width:"200px",
 height:"30px;"
}
var styles2 ={color:"yellow"};

var none = {color:"black",background:"white"};

 class Header extends React.Component{
  render()
  {
   return <div>
    <h2>{this.props.name} name of this is header component</h2>
    </div>
   
  }
 }

class Footer extends React.Component{
  render()
  {
   return <div> 
    <h2>This is footer component</h2>
   </div>
  }
}

class Hello extends React.Component{
render()
{
 return <div style={styles}>
  <h1 style={styles2}>Hello world</h1>
  <p>Hope this works</p>
  <h3>sum is {4+9}</h3>
  <Header name="Alex"/>
  <Header name="Max"/>
  <Footer/>
  </div>
}
}
ReactDOM.render(<Hello />,document.getElementById('mydiv'))


 // var name = React.createClass({
 //   render :function () {
 //    return (
 //     <div>
 //      <h1>Hello World</h1>
 //     </div>
 //    );
 //   }
 //  }
 // );

 //  ReactDOM.render(<name/>, document.getElementById("content"));