package com.concretepage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.concretepage.entity.Article;
import com.concretepage.service.ArticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("/user")
@Api(tags = "Redis Cache Calaogue", value = "Redis Cache Calaogu")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@ApiOperation(value = "Retrieve Articles By Id", nickname = "Retrieve Articles By Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved products"),
            @ApiResponse(code = 404, message = "No data found !!!")
    })
	@GetMapping("article/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable("id") Long id) {
		Article article = articleService.getArticleById(id);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}

	
	@ApiOperation(value = "Find All Articles", nickname = "Find All Articles")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved products"),
            @ApiResponse(code = 404, message = "No data found !!!")
    })
	@GetMapping("articles")
	public ResponseEntity<List<Article>> getAllArticles() {
		List<Article> list = articleService.getAllArticles();
		return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
	}

	
	@ApiOperation(value = "Create Articles", nickname = "Create Articles")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved products"),
            @ApiResponse(code = 404, message = "No data found !!!")
    })
	@PostMapping("article")
	public ResponseEntity<Void> addArticle(@RequestBody Article article, UriComponentsBuilder builder) {
		Article savedArticle = articleService.addArticle(article);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/article/{id}").buildAndExpand(savedArticle.getArticleId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update Articles", nickname = "Update Articles")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved products"),
            @ApiResponse(code = 404, message = "No data found !!!")
    })
	@PutMapping("article")
	public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
		articleService.updateArticle(article);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}

	
	
	@ApiOperation(value = "Delete Articles By Id", nickname = "Delete Articles By Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved products"),
            @ApiResponse(code = 404, message = "No data found !!!")
    })
	@DeleteMapping("article/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id) {
		articleService.deleteArticle(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}