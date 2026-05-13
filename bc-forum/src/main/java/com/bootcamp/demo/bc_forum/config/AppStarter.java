package com.bootcamp.demo.bc_forum.config;

import com.bootcamp.demo.bc_forum.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStarter implements CommandLineRunner {
  @Autowired ForumService forumService;

  public void run(String... args) throws Exception {
    // this.forumService.saveAllData();
  }
}
