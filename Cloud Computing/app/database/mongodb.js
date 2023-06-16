const mongoose = require("mongoose");

mongoose
  .connect("mongodb+srv://fix-rest-api:" + process.env.MONGO_ATLAS_PW + "@fix-rest-api.e0llwg5.mongodb.net/?retryWrites=true&w=majority")
  .then(() => console.log("Database Connected"))
  .catch((error) => console.log(error.message));
