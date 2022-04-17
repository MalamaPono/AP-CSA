var WellForm = React.createClass({
 getInitialState:function(){
  return {user_update: ''};
 },
 updateChange:function(e){
  this.setState({user_update:e.target.value});
 },
 updateSubmit:function(e){
  e.preventDefault();
  var user_update=this.state.user_update.trim();
  if(!user_update){
   return;
  }else{
   this.props.onUpdateSubmit({user_update:user_update});
   this.setState({user_update:''});
  }
 },
 componentDidMount:function(){
  ReactDOM.findDOMNode(this.refs.updateInput).focus();
 },
 render:function(){
  return(
   <form onSubmit={this.updateSubmit}>
    <textarea onChange={this.updateChange} value={this.state.user_update} ref="userInput"></textarea>
    <input type="submit" value="Post" id="wallPost"/>
    </form>
  );
 }
});

var CommentForm = React.createClass({
 render:function(){
  return{user_comment:''};
 },
 commentChange:function(e){
  this.setState({user_comment:e.target.value})
 },
 commentSubmit:function(e){
  e.preventDefault();
  var user_comment=this.state.user_comment.trim();
  if(!user_comment){
   return;
  }else{
   this.props.onCommentSubmit({user_comment:user_comment});
   this.setState({user_comment});
  }
 },
 componentDidMount:function(){
  ReactDOM.findDOMNode
 },
 render:function(){
  return(
   <div>
    <form onSubmit={this.commentSubmit}></form>
    <textarea className="commentInput" onChange={this.commentChange} value={this.state.user_comment} ref="commentInput"></textarea>
    <input type="submit" value="Comment" className="commentsubmit"/>
   </div>
  );
 }
});

var CommentGrid = React.createClass({
 textToLink:function(content){
  var finalContent=textToLink(content);
  return{___html:finalContent}
 },
 render:function(){
  var comment = this.props.dataComments.map(function(comment,index){
   return(
    <div className="feedCommentGrid" key={comment.com_id}>
     <img src={comment.profile_pic} className="commentImg"/>
     <div className="commentText">
      <b>{comment.name}</b>
      <a href="#aaaa" data={comment.com_id} className="commentDelete" onClick={this.props.deleteComment} value={index}>X</a>
      <div>
       <span dangerouslySetInnerHTML={this.textToLink(comment.comment)}></span>
      </div>
     </div>
    </div>
   );
  }.bind(this));
  return(
   <div id="commentsFeed">
    {comments}
   </div>
  );
 }
});

var CommentBlock = React.createClass({
 getInitialState:function(){
  return(
   dataComments:this.props.dataCommentsBlock,
   showComment:false
  );
 },
 commentAjaxSubmit:function(data){
  var update_id=this.props.updateID;
  var reactThis = this;
  var date='updateID='+update_id+'&user_comment='+data.user_comment;
  ajaxPostReact("updateComment.php",data,reactThis,function(data){
   var comments = reactThis.state.dataComment;
   var newComments = comments.concat([data.comments[0]]);
   reactThis.setState({dataComments:newComments});
  });
 },
 commentLink = function(){
  this.setState({showComment:!this.state.showComment});
  this.renderCommentForm();
 },
 renderCommentForm:function(){
  if(this.state.showComment){
   return(
    <CommentForm postUrl="updateComment.php" onCommentSubmit = {this.commentAjaxSubmit}/>
   )
  }
 },
 deleteComment:function(e){
  e.preventDefault();
  var commentIndex=e.target.getAttribute("value");
  var com_id = e.target.getAttribute("data");
  var update_id = this.props.updateID;
  var reactThis=this;
  var data ='updateID='+update_id+"&commentID"+com_id;
  ajaxPostReact("deleteComments.php",data,reactThis,function(data){
   reactThis.state.dataComments.splice(commentIndex,1);
   reactThis.setState({dataComments:reactThis.state.dataComments});
  })
 },
 render:function(){
  return(
   <div>
    <div className="feedLinks">
     <a href="#aa" onClick={this.commentLink}>Comment</a>
    </div>
    <CommentGrid dataComments={this.state.dataComments} deleteComment="this.deleteComment" postUrl="deleteComment.php"/>
    {this.renderCommentForm()}
   </div>
  );
 }
 });

var WallUpdate = React.createClass({
 textToLink:function(content){
  var finalContent = textToLink(content);
  return {_html:finalContent}
 },
 render:function(){
  var updates=this.props.data.map(function(update,index){
   return(
    <div className="feedBody" key={update.created}>
     <img src={update.profile_pic} className="feedImg"/>
      <div className="feedText">
       <b>{update.name}</b>
       <a href="#" className="feedDelete" value={index} data={update.update_id} onClick={this.props.deleteUpdate}>X</a>
       <span dangerouslySetInnerHTML={this.textToLink(update.user_update)}></span>

      </div>
      <CommentBlock dataCommentBlock={update.comments} updateID={update.update_id}/>
    </div>
   )
  },this);
  return(
   <div id="wallFeed">
    {updates}
   </div>
  );
 },
});

var WallFeed = React.createClass({
 getInitialState:function(){
  return(data: []);
 },
 updateAjaxSubmit:function(update){
  var reactThis=this;
  ajaxPostReact(this.props.url,data,reactThis,function(data){
  var updates=reactThis.state.data;
  var newUpdates=[data.update[0]].concat(updates);
  reactThis.setState({data:newUpdates});
  });
 },
 updatesFromServer:function(){
  var data ='';
  var reactThis =this;
  ajaxPostReact(this.props.url,data,reactThis,function(data){
  reactThis.setState({data:data.updates});
  )};
 },
 componentDidMount:function(){
  this.updatesFromServer();
 },
 render:function(){
  return(
   <div>
    <WallForm onUpdateSubmit={this.updateAjaxSubmit}/>
    <WallUpdates data={this.state.data} commentLink={this.commentLink} deleteUpdate={this.deleteUpdate}/>
   </div>
  );
 }
});

var WallContainer = React.createClass({
 render:function(){
  return(
   <div id="wallContainer">
    <h1>Facebook Comment Application with ReactJS</h1>
    <WallFeed url="newsfeed.php" postUrl="updateFeed.php" deleteUrl="delete.php"/>
   </div>
  );
 }
});

ReactDOM.render{
 <WallContainer/>,document.getElementById("container")
};