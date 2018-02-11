/**
 * 
 */
package aug.manas.accountservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import aug.manas.accountservice.model.ExpTransaction;
import aug.manas.accountservice.service.ExpTransactionService;
import aug.manas.accountservice.service.ExpUserService;


/**
 * @author shweta
 *
 */
@RestController
@RequestMapping("/api/trans")
public class AccountServiceController {
	private static final Logger logger = LoggerFactory.getLogger(AccountServiceController.class);

	@Autowired
	ExpUserService expUserService;
	
	@Autowired
	ExpTransactionService expTransactionService;
	/*
	 * /* Method to get all transactions for the user
	 */
	/*@RequestMapping(value = "/user/trans", method = RequestMethod.GET)
	public ResponseEntity<List<ExpTransaction>> listAllTransactionsForUser(@RequestParam(required = true) long id)
			throws ExpAccountServiceException {
		logger.info("Getting list of all transactions for User id " + id);

		User user = userService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			throw new ExpAccountServiceException("Unable to delete. User with id " + id + " not found.");
		} else {
			List<ExpTransaction> transLst = user.getTransactions();
			if (transLst == null || transLst.size() <= 0) {
				throw new ExpAccountServiceException("No transaction exists for User id " + id);
			} else {
				return new ResponseEntity<List<ExpTransaction>>(transLst, HttpStatus.OK);
			}
		}

	} */
	@RequestMapping(value = "/gettransactions", method = RequestMethod.GET)
	public void listAllTransactionForUser(@RequestParam(required = true) long uid,
			@RequestBody ExpTransaction trans, UriComponentsBuilder ucBuilder) {
		logger.debug("Inside addTransactionForUser");

			expTransactionService.addTransaction(trans);

	} 

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addTransactionForUser(@RequestParam(required = true) long uid,
			@RequestBody ExpTransaction trans, UriComponentsBuilder ucBuilder) {
		logger.debug("Inside addTransactionForUser");

			expTransactionService.addTransaction(trans);

	} 
}
