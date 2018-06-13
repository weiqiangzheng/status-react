(ns status-im.translations.en)

(def translations
  {;;common
   :members-title                        "Members"
   :not-implemented                      "!not implemented"
   :chat-name                            "Chat name"
   :notifications-title                  "Notifications and sounds"
   :offline                              "Offline"
   :disconnected                         "Connecting to peers..."
   :connection-problem                   "Messages connection problem"
   :mailserver-reconnect                 "Could not connect to mailserver. Tap to reconnect"
   :fetching-messages                    "Fetching messages..."
   :search-for                           "Search for..."
   :cancel                               "Cancel"
   :next                                 "Next"
   :open                                 "Open"
   :description                          "Description"
   :enter-url                            "Enter URL"
   :open-dapp                            "Open ÐApp"
   :url                                  "URL"
   :type-a-message                       "Type a message..."
   :type-a-command                       "Start typing a command..."
   :error                                "Error"
   :unknown-status-go-error              "Unknown status-go error"
   :node-unavailable                     "No ethereum node running"
   :add                                  "Add"
   :yes                                  "Yes"
   :no                                   "No"
   :on                                   "On"
   :off                                  "Off"
   :mailserver-connection-error          "Could not connect to mailserver"

   :camera-access-error                  "To grant the required camera permission, please go to your system settings and make sure that Status > Camera is selected."
   :photos-access-error                  "To grant the required photos permission, please go to your system settings and make sure that Status > Photos is selected."

   :status-console                       "Status Console"

   ;;sign in
   :intro-text                           "Status is an open source decentralized chat and Ethereum browser"
   :intro-text-description               "Status is built with the help of the community to help you use all the benefits of decentralized web in your mobile phone"
   :create-account                       "Create account"
   :already-have-account                 "I already have an account"
   :creating-your-account                "Creating your account on the blockchain. We can't touch it, no one can, except for you!"
   :password-placeholder                 "Type your password"
   :password-placeholder2                "Confirm your password"
   :name-placeholder                     "Display name"
   :password_error1                      "Password confirmation doesn't match password."
   :password-description                 "You'll need this password to open the app, confirm transactions and whenever you need to regain access on a new device or install."
   :name-description                     "This will be the name everybody who uses Status will see. You can change it later."
   :other-accounts                       "Other accounts"
   :sign-you-in                          "Signing you in…"

   :help-improve                         "Help improve Status\nby sharing usage patterns"
   :help-improve?                        "Help improve Status?"
   :help-improve-description             "We collect anonymous data to understand how Status is used and inform future development. Whilst we appreciate your help to make Status better, this is an optional choice and can be changed at any time."
   :learn-what-we-collect-link           "Learn more about what we collect"
   :share-usage-data                     "Share data"
   :dont-want-to-share                   "Do not share"
   :confirmation-title                   "Share data with Status?"
   :confirmation-text                    "Are you sure you want to share anonymous data with Status?"
   :confirmation-action                  "Share"

   ;;drawer
   :switch-users                         "Switch users"
   :logout-title                         "Log out?"
   :logout-are-you-sure                  "Are you sure you want\nto log out?"
   :logout                               "Log out"
   :current-network                      "Current network"

   ;;home
   :home                                 "Home"
   :no-recent-chats                      "There are no recent Chats or DApps here yet. Tap the “Plus” button to see the list of Dapps or discover people to chat with."
   :welcome-to-status                    "Welcome to Status"
   :welcome-to-status-description        "Here you can securely chat with people, or browse and interact with DApps. Tap the “Plus” icon to begin."

   ;;chat
   :is-typing                            "is typing"
   :and-you                              "and you"
   :search-chat                          "Search chat"
   :members                              {:one   "1 member"
                                          :other "{{count}} members"
                                          :zero  "no members"}
   :members-active                       {:one   "1 member"
                                          :other "{{count}} members"
                                          :zero  "no members"}
   :public-group-status                  "Public"
   :active-online                        "Online"
   :active-unknown                       "Unknown"
   :available                            "Available"
   :no-messages                          "No messages"
   :suggestions-requests                 "Requests"
   :suggestions-commands                 "Commands"
   :faucet-success                       "Faucet request has been received"
   :faucet-error                         "Faucet request error"
   :network-mismatch                     "Network mismatch"

   ;;sync
   :sync-in-progress                     "Syncing..."
   :sync-synced                          "In sync"

   ;;messages
   :status-sending                       "Sending..."
   :status-pending                       "Pending"
   :status-sent                          "Sent"
   :status-seen-by-everyone              "Seen by everyone"
   :status-seen                          "Seen"
   :status-delivered                     "Delivered"
   :status-failed                        "Failed"
   :status-not-sent                      "Not sent. Tap for options"
   :message-not-sent                     "Message not sent"
   :resend-message                       "Resend"
   :delete-message                       "Delete message"

   ;;datetime
   :datetime-ago-format                  "{{number}} {{time-intervals}} {{ago}}"
   :datetime-second                      {:one   "second"
                                          :other "seconds"}
   :datetime-minute                      {:one   "minute"
                                          :other "minutes"}
   :datetime-hour                        {:one   "hour"
                                          :other "hours"}
   :datetime-day                         {:one   "day"
                                          :other "days"}
   :datetime-ago                         "ago"
   :datetime-yesterday                   "yesterday"
   :datetime-today                       "today"

   ;;profile
   :profile                              "Profile"
   :view-profile                         "View profile"
   :edit-profile                         "Edit profile"
   :main-currency                        "Main currency"
   :message                              "Message"
   :notifications                        "Notifications"
   :not-specified                        "Not specified"
   :public-key                           "Public key"
   :phone-number                         "Phone number"
   :share-contact-code                   "Share my contact code"
   :update-status                        "Update your status..."
   :add-a-status                         "Add a status..."
   :status-prompt                        "Set your status. Using #hastags will help others discover you and talk about what's on your mind"
   :contact-code                         "Contact code"
   :add-to-contacts                      "Add to contacts"
   :in-contacts                          "In contacts"
   :remove-from-contacts                 "Remove from contacts"
   :start-conversation                   "Start conversation"
   :send-message                         "Send message"
   :testnet-text                         "You’re on the {{testnet}} Testnet. Do not send real ETH or SNT to your address"
   :mainnet-text                         "You’re on the Mainnet. Real ETH will be sent"
   :dev-mode                             "Development mode"
   :backup-your-seed                     "Backup your Seed Phrase"

   ;;seed
   :your-data-belongs-to-you             "If you lose your seed phrase you lose your data and funds"
   :your-data-belongs-to-you-description "Status can’t help you recover your account if you lose your seed phrase. You are in charge of the security of your data, and backing up your seed phrase is the best safeguard."
   :ok-continue                          "Ok, continue"
   :your-seed-phrase                     "Your seed phrase"
   :your-seed-phrase-description         "This is your seed phrase. You use it to prove that this is your wallet. You only get to see it once! Write it on paper and keep it in a secure place. You will need it if you lose or reinstall your wallet."
   :enter-word                           "Enter word"
   :check-your-seed                      "Check your seed phrase"
   :wrong-word                           "Wrong word"
   :are-you-sure?                        "Are you sure?"
   :are-you-sure-description             "You will not be able to see the whole seed phrase again"
   :you-are-all-set                      "You’re all set!"
   :you-are-all-set-description          "Now if you lose your phone you can restore your account and wallet using the seed phrase and password."
   :ok-got-it                            "Ok, got it"
   :backup-seed-phrase                   "Backup Seed Phrase"
   :step-i-of-n                          "Step {{step}} of {{number}}"
   :word-n-description                   "In order to check if you have backed up your seed phrase correctly, enter the word #{{number}} above."
   :word-n                               "Word #{{number}}"

   :wallet-onboarding-title              "Simple and secure cryptocurrency wallet"
   :wallet-onboarding-description        "Send, receive, and store your cryptocurrency with the Status Wallet"
   :wallet-onboarding-set-up             "Let’s get set up"
   :wallet-set-up-title                  "Set up your wallet"
   :wallet-set-up-signing-phrase         "This is your personal transaction phrase that you’ll use everytime you make a transaction. Make sure to write it down on a piece of paper, store it somewhere, and only confirm transactions when you see these three words."
   :wallet-set-up-confirm-title          "Wrote it down?"
   :wallet-set-up-confirm-description    "You won’t be able to see your 3-word transaction phrase again after this."
   :wallet-backup-seed-title             "Backup your Seed Phrase"
   :wallet-backup-seed-description       "This will help you to keep your money safe"

   ;;make_photo
   :image-source-title                   "Edit picture"
   :image-source-make-photo              "Capture"
   :image-source-gallery                 "Select from gallery"

   ;;sharing
   :sharing-copy-to-clipboard            "Copy to clipboard"
   :sharing-share                        "Share..."
   :sharing-cancel                       "Cancel"

   :browsing-title                       "Browse"
   :browsing-open-in-web-browser         "Open in web browser"
   :browsing-open-in-browser             "Open in browser"
   :browsing-cancel                      "Cancel"

   ;;sign-up
   :contacts-syncronized                 "Your contacts have been synchronized"
   :confirmation-code                    (str "Thanks! We've sent you a text message with a confirmation "
                                              "code. Please provide that code to confirm your phone number")
   :incorrect-code                       (str "Sorry the code was incorrect, please enter it again")
   :phew-here-is-your-passphrase         "Phew, that was hard. Here is your passphrase, *write this down and keep it safe!* You will need it to recover your account."
   :here-is-your-passphrase              "Here is your passphrase, *write this down and keep this safe!* You will need it to recover your account."
   :here-is-your-signing-phrase          "Here is your signing phrase. You will use it to verify your transactions. *Write it down and keep it safe!*"
   :phone-number-required                "Tap here to validate your phone number & I'll find your friends."
   :shake-your-phone                     "Found a bug or have a suggestion? Just ~shake~ your phone!"
   :intro-status                         "Chat with me to setup your account and change your settings."
   :intro-message1                       "Welcome to Status!\nTap this message to set your password and get started."
   :account-generation-message           "Gimmie a sec, I gotta do some crazy math to generate your account!"
   :move-to-internal-failure-message     "We need to move some important files from external to internal storage. To do this, we need your permission. We won't be using external storage in future versions."
   :debug-enabled                        "Debug server has been launched! You can now execute *status-dev-cli scan* to find the server from your computer on the same network."

   ;;phone types
   :phone-e164                           "International 1"
   :phone-international                  "International 2"
   :phone-national                       "National"
   :phone-significant                    "Significant"

   ;;chats
   :new                                  "New"
   :new-chat                             "New chat"
   :start-new-chat                       "Start new chat"
   :start-group-chat                     "Start group chat"
   :invite-friends                       "Invite friends"
   :get-status-at                        "Get Status at http://status.im?refCode={{address}}"
   :chats                                "Chats"
   :delete-chat                          "Delete chat"
   :group-chat                           "Group chat"
   :group-info                           "Group info"
   :delete-chat-confirmation             "Are you sure you want to delete this chat?"
   :delete-group-chat-confirmation       "Are you sure you want to delete this group chat?"
   :new-group-chat                       "New group chat"
   :new-public-group-chat                "Join public chat"
   :public-chat                          "Public chat"
   :edit-chats                           "Edit chats"
   :search-chats                         "Search chats"
   :empty-topic                          "Empty topic"
   :topic-format                         "Wrong format [a-z0-9\\-]+"
   :topic-name-error                     "A topic name can contain only following characters: a-z -"
   :public-group-topic                   "Topic"
   :set-a-topic                          "Set a topic"
   :empty-chat-description               "There are no messages \nin this chat yet"
   :empty-chat-description-console       "Look under the hood! Console is a javascript runtime environment that exposes the whole web3 API. Type \"web3.\" to get started."
   :empty-chat-description-one-to-one    "Any messages you send here are encrypted and can only be read by you and "
   :counter-99-plus                      "99+"
   :show-more                            "Show more"
   :show-less                            "Show less"

   ;;discover
   :discover                             "Discover"
   :none                                 "None"
   :search-tags                          "Type your search tags here"
   :popular-tags                         "Popular #hashtags"
   :recent                               "Recent statuses"
   :no-statuses-found                    "No statuses found"
   :chat                                 "Chat"
   :all                                  "All"
   :public-chats                         "Public chats"
   :soon                                 "Soon"
   :public-chat-user-count               "{{count}} people"
   :dapps                                "ÐApps"
   :dapp-profile                         "ÐApp profile"
   :no-statuses-discovered               "No statuses discovered"
   :no-statuses-discovered-body          "When somebody posts\na status you will see it here."
   :no-hashtags-discovered-title         "No #hashtags discovered"
   :no-hashtags-discovered-body          "When a #hashtag becomes\npopular you will see it here."

   ;;settings
   :settings                             "Settings"

   ;;contacts
   :contacts                             "Contacts"
   :new-contact                          "New contact"
   :delete-contact                       "Delete contact"
   :delete-contact-confirmation          "This contact will be removed from your contacts"
   :remove-from-group                    "Remove from group"
   :edit-contacts                        "Edit contacts"
   :search-contacts                      "Search contacts"
   :contacts-group-new-chat              "Start new chat"
   :choose-from-contacts                 "Choose from contacts"
   :no-contacts                          "No contacts yet"
   :show-qr                              "Show QR code"
   :qr-code-public-key-hint              "Share this code to \nstart chatting"
   :enter-address                        "Enter address"
   :enter-contact-code                   "Enter contact code"
   :more                                 "more"

   ;;group-settings
   :remove                               "Remove"
   :save                                 "Save"
   :create                               "Create"
   :delete                               "Delete"
   :delete-confirmation                  "Delete?"
   :leave                                "Leave"
   :leave-confirmation                   "Leave?"
   :clear                                "Clear"
   :clear-history                        "Clear history"
   :clear-history-title                  "Clear history?"
   :clear-history-confirmation-content   "Are you sure you want to clear this chat history?"
   :clear-history-confirmation           "Clear history?"
   :clear-history-action                 "Clear"
   :mute-notifications                   "Mute notifications"
   :leave-group-action                   "Leave"
   :leave-group-title                    "Leave?"
   :leave-group-confirmation             "Are you sure you want to leave this group?"
   :leave-chat                           "Leave chat"
   :leave-group-chat-confirmation        "Are you sure you want to leave this group?"
   :leave-group-chat                     "Leave group chat"
   :leave-group                          "Leave group"
   :remove-from-chat                     "Remove from chat"
   :delete-chat-title                    "Delete chat?"
   :leave-public-chat                    "Leave public chat"
   :chat-settings                        "Chat settings"
   :edit                                 "Edit"
   :add-members                          "Add members"

   ;;commands
   :chat-send-eth                        "{{amount}} ETH"

   ;;new-group
   :new-group                            "New group"
   :reorder-groups                       "Reorder groups"
   :edit-group                           "Edit group"
   :delete-group                         "Delete group"
   :delete-group-confirmation            "This group will be removed from your groups. This will not affect your contacts"
   :delete-group-prompt                  "This will not affect your contacts"
   :contact-s                            {:one   "contact"
                                          :other "contacts"}

   ;;protocol
   :received-invitation                  "received chat invitation"
   :removed-from-chat                    "removed you from group chat"
   :left                                 "left"
   :invited                              "invited"
   :removed                              "removed"
   :You                                  "You"

   ;;new-contact
   :add-new-contact                      "Add new contact"
   :scan-qr                              "Scan QR code"
   :name                                 "Name"
   :specify-name                         "Specify a name"
   :address-explication                  "Your public key is used to generate your address on Ethereum and is a series of numbers and letters. You can find it easily in your profile"
   :unable-to-read-this-code             "Unable to read this code"
   :use-valid-contact-code               "Please scan a valid contact code"
   :enter-valid-public-key               "Please enter a valid public key or scan a QR code"
   :contact-already-added                "The contact has already been added"
   :can-not-add-yourself                 "You can't add yourself"
   :unknown-address                      "Unknown address"

   ;;login
   :connect                              "Connect"
   :address                              "Address"
   :password                             "Password"
   :sign-in-to-status                    "Sign in to Status"
   :sign-in                              "Sign in"
   :sign-in-to-another                   "Sign in to another account"
   :wrong-password                       "Wrong password"
   :enter-password                       "Enter password"

   ;;recover
   :passphrase                           "Passphrase"
   :recover                              "Recover"
   :twelve-words-in-correct-order        "12 words in correct order"
   :enter-12-words                       "Enter the 12 words of your seed phrase"

   ;;accounts
   :recover-access                       "Recover access"
   :create-new-account                   "Create new account"
   :add-existing-account                 "Add existing account"

   ;;wallet-qr-code
   :done                                 "Done"

   ;;validation
   :invalid-phone                        "Invalid phone number"
   :amount                               "Amount"
   :invalid-number                       "Invalid number"

   ;;transactions
   :confirm                              "Confirm"
   :transaction                          "Transaction"
   :unsigned-transaction-expired         "Unsigned transaction expired"
   :status                               "Status"
   :recipient                            "Recipient"
   :specify-recipient                    "Specify recipient..."
   :recipient-code                       "Enter recipient address"
   :recent-recipients                    "Contacts"
   :to                                   "To"
   :from                                 "From"
   :data                                 "Data"
   :got-it                               "Got it"
   :block                                "Block"
   :hash                                 "Hash"
   :gas-limit                            "Gas limit"
   :gas-price                            "Gas price"
   :gas-used                             "Gas used"
   :cost-fee                             "Cost/Fee"
   :nonce                                "Nonce"
   :confirmations                        "Confirmations"
   :confirmations-helper-text            "Please wait for at least 12 confirmations to make sure your transaction is processed securely"
   :copy-transaction-hash                "Copy transaction hash"
   :open-on-etherscan                    "Open on Etherscan.io"
   :incoming                             "Incoming"
   :outgoing                             "Outgoing"
   :pending                              "Pending"
   :postponed                            "Postponed"

   ;;webview
   :web-view-error                       "Unable to load page"

   ;;testfairy warning
   :testfairy-title                      "Warning!"
   :testfairy-message                    "You are using an app installed from a nightly build. For testing purposes this build includes session recording if wifi connection is used, so all your interactions with this app is saved (as video and logs) and might be used by our development team to investigate possible issues. Saved video/logs do not include your passwords. Recording is done only if the app is installed from a nightly build. Nothing is recorded if the app is installed from PlayStore or TestFlight."

   ;; wallet
   :wallet                               "Wallet"
   :wallets                              "Wallets"
   :your-wallets                         "Your wallets"
   :main-wallet                          "Main Wallet"
   :wallet-error                         "Error loading data"
   :wallet-send                          "Send"
   :wallet-send-token                    "Send {{symbol}}"
   :wallet-request                       "Request"
   :wallet-exchange                      "Exchange"
   :wallet-asset                         "Asset"
   :wallet-assets                        "Assets"
   :wallet-add-asset                     "Add asset"
   :wallet-total-value                   "Total value"
   :wallet-settings                      "Wallet settings"
   :wallet-manage-assets                 "Manage Assets"
   :sign-message                         "Sign Message"
   :signing-message-phrase-description   "Sign the message by entering your password. Make sure that the words above match your secret signing phrase"
   :signing-phrase-description           "Sign the transaction by entering your password. Make sure that the words above match your secret signing phrase"
   :wallet-insufficient-funds            "Insufficient funds"
   :receive                              "Receive"
   :request-qr-legend                    "Share this code to receive assets"
   :send-request                         "Send request"
   :send-transaction-request             "Send a transaction request"
   :share                                "Share"
   :eth                                  "ETH"
   :gwei                                 "Gwei"
   :currency                             "Currency"
   :usd-currency                         "USD"
   :currency-display-name-aed            "Emirati Dirham"
   :currency-display-name-afn            "Afghanistan Afghani"
   :currency-display-name-ars            "Argentine Peso"
   :currency-display-name-aud            "Australian Dollar"
   :currency-display-name-bbd            "Barbados Dollar"
   :currency-display-name-bdt            "Bangladeshi Taka"
   :currency-display-name-bgn            "Bulgarian Lev"
   :currency-display-name-bhd            "Bahraini Dinar"
   :currency-display-name-bmd            "Bermuda Dollar"
   :currency-display-name-bnd            "Brunei Darussalam Dollar"
   :currency-display-name-bob            "Bolivia Bolíviano"
   :currency-display-name-brl            "Brazil Real"
   :currency-display-name-btn            "Bhutanese Ngultrum"
   :currency-display-name-bzd            "Belize Dollar"
   :currency-display-name-cad            "Canada Dollar"
   :currency-display-name-chf            "Switzerland Franc"
   :currency-display-name-clp            "Chile Peso"
   :currency-display-name-cny            "China Yuan Renminbi"
   :currency-display-name-cop            "Colombia Peso"
   :currency-display-name-crc            "Costa Rica Colon"
   :currency-display-name-czk            "Czech Koruna"
   :currency-display-name-dkk            "Denmark Krone"
   :currency-display-name-dop            "Dominican Republic Peso"
   :currency-display-name-egp            "Egypt Pound"
   :currency-display-name-etb            "Ethiopian Birr"
   :currency-display-name-eur            "Euro"
   :currency-display-name-gbp            "British Pound"
   :currency-display-name-gel            "Georgian Lari"
   :currency-display-name-ghs            "Ghana Cedi"
   :currency-display-name-gmd            "Gambian dalasi"
   :currency-display-name-gyd            "Guyana Dollar"
   :currency-display-name-hkd            "Hong Kong Dollar"
   :currency-display-name-hrk            "Croatia Kuna"
   :currency-display-name-huf            "Hungary Forint"
   :currency-display-name-idr            "Indonesia Rupiah"
   :currency-display-name-ils            "Israel Shekel"
   :currency-display-name-inr            "India Rupee"
   :currency-display-name-isk            "Iceland Krona"
   :currency-display-name-jmd            "Jamaica Dollar"
   :currency-display-name-jpy            "Japanese Yen"
   :currency-display-name-kes            "Kenyan Shilling"
   :currency-display-name-krw            "Korea (South) Won"
   :currency-display-name-kwd            "Kuwaiti Dinar"
   :currency-display-name-kyd            "Cayman Islands Dollar"
   :currency-display-name-kzt            "Kazakhstan Tenge"
   :currency-display-name-lak            "Laos Kip"
   :currency-display-name-lkr            "Sri Lanka Rupee"
   :currency-display-name-lrd            "Liberia Dollar"
   :currency-display-name-ltl            "Lithuanian Litas"
   :currency-display-name-mad            "Moroccan Dirham"
   :currency-display-name-mdl            "Moldovan Leu"
   :currency-display-name-mkd            "Macedonia Denar"
   :currency-display-name-mnt            "Mongolia Tughrik"
   :currency-display-name-mur            "Mauritius Rupee"
   :currency-display-name-mwk            "Malawian Kwacha"
   :currency-display-name-mxn            "Mexico Peso"
   :currency-display-name-myr            "Malaysia Ringgit"
   :currency-display-name-mzn            "Mozambique Metical"
   :currency-display-name-nad            "Namibia Dollar"
   :currency-display-name-ngn            "Nigeria Naira"
   :currency-display-name-nio            "Nicaragua Cordoba"
   :currency-display-name-nok            "Norway Krone"
   :currency-display-name-npr            "Nepal Rupee"
   :currency-display-name-nzd            "New Zealand Dollar"
   :currency-display-name-omr            "Oman Rial"
   :currency-display-name-pen            "Peru Sol"
   :currency-display-name-pgk            "Papua New Guinean Kina"
   :currency-display-name-php            "Philippines Peso"
   :currency-display-name-pkr            "Pakistan Rupee"
   :currency-display-name-pln            "Poland Zloty"
   :currency-display-name-pyg            "Paraguay Guarani"
   :currency-display-name-qar            "Qatar Riyal"
   :currency-display-name-ron            "Romania Leu"
   :currency-display-name-rsd            "Serbia Dinar"
   :currency-display-name-rub            "Russia Ruble"
   :currency-display-name-sar            "Saudi Arabia Riyal"
   :currency-display-name-sek            "Sweden Krona"
   :currency-display-name-sgd            "Singapore Dollar"
   :currency-display-name-sos            "Somalia Shilling"
   :currency-display-name-srd            "Suriname Dollar"
   :currency-display-name-thb            "Thailand Baht"
   :currency-display-name-ttd            "Trinidad and Tobago Dollar"
   :currency-display-name-twd            "Taiwan New Dollar"
   :currency-display-name-tzs            "Tanzanian Shilling"
   :currency-display-name-try            "Turkish Lira"
   :currency-display-name-uah            "Ukraine Hryvnia"
   :currency-display-name-ugx            "Ugandan Shilling"
   :currency-display-name-uyu            "Uruguay Peso"
   :currency-display-name-usd            "United States Dollar"
   :currency-display-name-vef            "Venezuela Bolívar"
   :currency-display-name-vnd            "Viet Nam Dong"
   :currency-display-name-yer            "Yemen Rial"
   :currency-display-name-zar            "South Africa Rand"
   :amount-placeholder                   "Specify amount..."
   :transactions                         "Transactions"
   :transaction-details                  "Transaction details"
   :transaction-failed                   "Transaction failed"
   :transactions-sign                    "Sign"
   :transactions-sign-all                "Sign all"
   :transactions-sign-transaction        "Sign transaction"
   :transactions-sign-later              "Sign later"
   :transactions-delete                  "Delete transaction"
   :transactions-delete-content          "Transaction will be removed from 'Unsigned' list"
   :transactions-history                 "Transaction history"
   :transactions-unsigned                "Unsigned"
   :transactions-history-empty           "No transactions in your history yet"
   :transactions-unsigned-empty          "You don't have any unsigned transactions"
   :transactions-filter-title            "Filter history"
   :transactions-filter-tokens           "Tokens"
   :transactions-filter-type             "Type"
   :transactions-filter-select-all       "Select all"
   :view-transaction-details             "View transaction details"
   :transaction-description              "Please wait for at least 12 confirmations to make sure your transaction is processed securely"
   :transaction-sent                     "Transaction sent"
   :transaction-moved-text               "The transaction will remain in the 'Unsigned' list for the next 5 mins"
   :transaction-moved-title              "Transaction moved"
   :sign-later-title                     "Sign transaction later?"
   :sign-later-text                      "Check the transaction history to sign this transaction"
   :not-applicable                       "Not applicable for unsigned transactions"
   :send-transaction                     "Send transaction"
   :new-request                          "New request"
   :request-transaction                  "Request transaction"
   :receive-transaction                  "Receive transaction"
   :new-transaction                      "New Transaction"
   :transaction-history                  "Transaction History"
   :new-contract                         "New Contract"

   ;; Wallet Send
   :wallet-choose-recipient              "Choose Recipient"
   :wallet-choose-from-contacts          "Choose From Contacts"
   :wallet-address-from-clipboard        "Use Address From Clipboard"
   :wallet-invalid-address               "Invalid address: \n {{data}}"
   :wallet-invalid-chain-id              "Network does not match: \n {{data}} but current chain is {{chain}}"
   :changed-asset-warning                "Asset was changed from {{old}} to {{new}}"
   :changed-amount-warning               "Amount was changed from {{old}} to {{new}}"
   :wallet-browse-photos                 "Browse Photos"
   :wallet-advanced                      "Advanced"
   :wallet-transaction-fee               "Transaction Fee"
   :wallet-transaction-fee-details       "Gas limit is the amount of gas to send with your transaction. Increasing this number will not get your transaction processed faster"
   :wallet-transaction-total-fee         "Total Fee"
   :validation-amount-invalid-number     "Amount is not a valid number"
   :validation-amount-is-too-precise     "Amount is too precise. Max number of decimals is {{decimals}}."
   :scan-qr-code                         "Scan a QR code with a wallet address"
   :reset-default                        "Reset to default"

   ;; network settings
   :new-network                          "New network"
   :add-network                          "Add network"
   :add-new-network                      "Add new network"
   :add-wnode                            "Add mailserver"
   :existing-networks                    "Existing networks"
   :delete-network-title                 "Delete network?"
   :delete-network-confirmation          "Are you sure you want to delete this network?"
   ;; TODO(dmitryn): come up with better description/naming. Suggested namings: Mailbox and Master Node
   :existing-wnodes                      "Existing mailservers"
   :add-mailserver                       "Add Mailserver"
   :mailserver-details                   "Mailserver details"
   :delete-mailserver-title              "Delete mailserver"
   :delete-mailserver-are-you-sure       "Are you sure you want to delete this mailserver?"
   :delete-mailserver                    "Delete mailserver"
   :mailserver-address                   "Mailserver address"
   :specify-mailserver-address           "Specify a mailserver address"
   :add-json-file                        "Add a JSON file"
   :paste-json-as-text                   "Paste JSON as text"
   :paste-json                           "Paste JSON"
   :specify-rpc-url                      "Specify a RPC URL"
   :edit-network-config                  "Edit network config"
   :connected                            "Connected"
   :process-json                         "Process JSON"
   :error-processing-json                "Error processing JSON"
   :rpc-url                              "RPC URL"
   :network-chain                        "Network chain"
   :network                              "Network"
   :network-details                      "Network details"
   :remove-network                       "Remove network"
   :network-settings                     "Network settings"
   :offline-messaging                    "Mailserver"
   :offline-messaging-settings           "Mailserver settings"
   :edit-network-warning                 "Be careful, editing the network data may disable this network for you"
   :delete-network-error                 "Please connect to a different network before deleting this one"
   :connecting-requires-login            "Connecting to another network requires login"
   :close-app-title                      "Warning!"
   :close-app-content                    "The app will stop and close. When you reopen it, the selected network will be used"
   :close-app-button                     "Confirm"
   :connect-wnode-content                "Connect to {{name}}?"

   ;; Bootnodes
   :bootnodes                            "Bootnodes"
   :bootnodes-settings                   "Bootnodes settings"
   :bootnodes-enabled                    "Bootnodes enabled"
   :bootnode-address                     "Bootnode address"
   :add-bootnode                         "Add bootnode"
   :bootnode-details                     "Bootnode details"
   :specify-bootnode-address             "Specify bootnode address"
   :delete-bootnode-title                "Delete bootnode"
   :delete-bootnode-are-you-sure         "Are you sure you want to delete this bootnode?"
   :delete-bootnode                      "Delete bootnode"

   :main-networks                        "Main networks"
   :test-networks                        "Test networks"
   :custom-networks                      "Custom networks"

   :mainnet-network                      "Main network"
   :ropsten-network                      "Ropsten test network"
   :rinkeby-network                      "Rinkeby test network"

   ;; browser
   :browser                              "Browser"
   :enter-dapp-url                       "Enter a ÐApp URL"
   :dapp                                 "ÐApp"
   :selected                             "Selected"
   :selected-dapps                       "Selected ÐApps"
   :browser-warning                      "Connection is not proven secure. Make sure you trust this site before signing transactions or entering personal data."})
