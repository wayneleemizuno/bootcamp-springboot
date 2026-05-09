package com.bootcamp.demo.bc_forum.controller;

import com.bootcamp.demo.bc_forum.dto.UserCommentDto;
import com.bootcamp.demo.bc_forum.dto.UserDetailDto;
import com.bootcamp.demo.bc_forum.service.ForumService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class ForumController {
  @Autowired ForumService forumService;

  @GetMapping(value = "/userdetail")
  public List<UserDetailDto> getUserDetails() {
    return this.forumService.getUserDetails();
  }

  @GetMapping(value = "/usercomments")
  public UserCommentDto getUserComments(@RequestParam Long id) {
    return this.forumService.getUserComments(id);
  }

  // @GetMapping("path")
  // public String getUrl(@RequestBody RequestUrlDto url) {

  // }

}
