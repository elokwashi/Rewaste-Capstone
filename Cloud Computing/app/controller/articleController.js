const httpStatus = require("http-status");
const Response = require("../model/Response");
const Article = require("../model/Article");
const articleValidator = require("../utils/articleValidator");
const uploadImage = require('../utils/upload')
const processFile = require("../middleware/uploadArticleFile");

const postArticles = async (req, res) => {
  let response = null;
  try {
    await processFile(req, res);
    
    if (!req.file) {
      const response = new Response.Error(400, "Please upload a image!" );
      return res.status(httpStatus.BAD_REQUEST).json(response);
    }
    
    const {
      name,
      description,
    } = await articleValidator.validateAsync(req.body);

    let article = await Article.findOne({ name });
    if(article) {
      response = new Response.Error(true, "Article already exist!");
      res.status(httpStatus.BAD_REQUEST).json(response);
      return;
    }
    const upload = await uploadImage(req.file, "images/")

    article = new Article({
      imageUrl: upload.url,
      name,
      description
    }); 

    const articleSave = await article.save();
    response = new Response.Success(false, "Success");
    res.status(httpStatus.OK).json(response);
  } catch (error) {
    response = new Response.Error(true, error.message);
    res.status(httpStatus.BAD_REQUEST).json(response);
  }
};

function checkName (article, params) {
  return article.name === params
}

const getArticles = async (req, res) => {
  let q = req.query.q;

  let response = null;
  try {
    let articles = await Article.find();
    
    if (q){
      articles = articles.filter( (article)=> { 
        //console.log (article.name)
        //console.log (q)
        return article.name.toLowerCase().includes(q.toLowerCase())
      }) 
    }

    response = new Response.Success(false, null, articles);
    res.status(httpStatus.OK).json(response); 
  } catch (error) {
    response = new Response.Error(true, error.message);
    res.status(httpStatus.BAD_REQUEST).json(response);
  }
};

const updateArticle = async (req, res) => {
  let response = null;
  const errorMessages = "Article not found!"; 
  try {
    const findArticle = await Article.findByIdAndUpdate(req.query.id, req.body); 
    await articleValidator.validateAsync(req.body);
    if(!findArticle){
        response = new Response.Error(true, errorMessages);
        res.status(httpStatus.BAD_REQUEST).json(response);
        return;
    };
    response = new Response.Success(false, null, findArticle);
    res.status(httpStatus.OK).json(response);
  } catch (error) {
    response = new Response.Error(true, error.message);
    res.status(httpStatus.BAD_REQUEST).json(response);
  }
};

const deleteArticle = async (req, res) => {
  let response = null;
  const notFoundId = "Article ID not found!"; 
  try {
    const deleteArticle = await Article.findByIdAndDelete(req.query.id);
    if(!deleteArticle){
      response = new Response.Error(true, notFoundId);
      res.status(httpStatus.BAD_REQUEST).json(response);
      return;
    }
    response = "Delete article success!"
    res.status(httpStatus.OK).json({ message: response});
  } catch (error) {
    response = new Response.Error(true, error.message);
    res.status(httpStatus.BAD_REQUEST).json(response);
  }
};

module.exports = { getArticles, postArticles, updateArticle, deleteArticle };
