const Joi = require("joi");

const articleValidator = Joi.object({
  name: Joi.string().required(),
  description: Joi.string().required(),
});

module.exports = articleValidator;
